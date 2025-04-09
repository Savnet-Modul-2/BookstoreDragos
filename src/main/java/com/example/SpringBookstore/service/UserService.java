package com.example.SpringBookstore.service;

import com.example.SpringBookstore.dto.UserDTO;
import com.example.SpringBookstore.entity.User;
import com.example.SpringBookstore.exceptionHandling.exception.BadRequestException;
import com.example.SpringBookstore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public User create(User userToCreate) {
        if (userToCreate.getId() != null) {
            throw new RuntimeException("Cannot provide an ID when creating a new user.");
        }

        userToCreate.setPassword(DigestUtils.md5DigestAsHex(userToCreate.getPassword().getBytes(StandardCharsets.UTF_8)));

        return userRepository.save(userToCreate);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found."));
    }

    public Page<User> findAll(Integer pageSize) {
        Pageable pageable = pageSize != null ? PageRequest.of(0, pageSize) : Pageable.unpaged();

        return userRepository.findAll(pageable);
    }

    public User update(Long userId, UserDTO userUpdate) {
        User userToUpdate = findById(userId);

        userToUpdate.setFirstName(userUpdate.getFirstName());
        userToUpdate.setLastName(userUpdate.getLastName());
        userToUpdate.setGender(userUpdate.getGender());
        userToUpdate.setAge(userUpdate.getAge());
        userToUpdate.setBirthDate(userUpdate.getBirthDate());
        userToUpdate.setEmailAddress(userUpdate.getEmailAddress());
        userToUpdate.setPassword(userUpdate.getPassword());

        return userRepository.save(userToUpdate);
    }

    public void delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Cannot delete user. User with ID " + userId + " not found.");
        }

        userRepository.deleteById(userId);
    }

    public void sendEmail(Long userId) {
        User user = findById(userId);
        emailService.sendEmail(user.getEmailAddress(), "Bookstore Email", "This is an email for " + user.getFirstName() + " " + user.getLastName() + ".");
    }

    public void sendVerificationEmail(User user) {
        if (!user.getVerifiedAccount()) {
            String verificationCode = emailService.generateVerificationCode();

            user.setVerificationCode(verificationCode);
            user.setVerificationCodeGenerationTime(LocalDateTime.now());

            userRepository.save(user);

            emailService.sendVerificationEmail(user.getEmailAddress(), verificationCode, emailService.getMaximumVerificationTime());
        }
    }

    public void resendVerificationEmail(Long userId) {
        User user = findById(userId);

        long remainingTime = emailService.getMaximumVerificationTime() - Duration.between(user.getVerificationCodeGenerationTime(), LocalDateTime.now()).toMinutes();

        if (remainingTime > 1) {
            emailService.sendVerificationEmail(user.getEmailAddress(), user.getVerificationCode(), remainingTime);
        } else {
            sendVerificationEmail(user);
        }
    }

    public User verifyAccount(Long userId, String verificationCode) {
        User user = findById(userId);

        if (Duration.between(user.getVerificationCodeGenerationTime(), LocalDateTime.now()).toMinutes() > 5) {
            throw new BadRequestException("Verification code expired. Request a new verification code.");
        } else if (!user.getVerificationCode().equals(verificationCode)) {
            throw new BadRequestException("User account verification unsuccessful. Invalid verification code.");
        } else {
            user.setVerificationCodeGenerationTime(null);
            user.setVerificationCode(null);

            user.setVerifiedAccount(true);

            userRepository.save(user);
        }

        return user;
    }

    public User login(String emailAddress, String password) {
        User user = userRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new EntityNotFoundException("User with email address " + emailAddress + " not found."));

        String hashedPassword = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        if (!user.getEmailAddress().equals(emailAddress) || !hashedPassword.equals(user.getPassword())) {
            throw new BadRequestException("Login unsuccessful. Invalid username or password.");
        }

        return user;
    }
}
