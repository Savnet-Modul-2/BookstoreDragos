package com.example.Spring_Initializr_Bookstore.service;

import com.example.Spring_Initializr_Bookstore.entities.User;
import com.example.Spring_Initializr_Bookstore.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String sender;

    public User checkUser(Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + "not found."));

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " does not have an email address.");
        }

        return user;
    }

    public void send(String recipient, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setFrom(sender);
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(text);

        javaMailSender.send(email);
    }

    public void sendVerification(User user) {
        if (!user.getVerifiedAccount()) {
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeGenerationTime(LocalDateTime.now());
            userRepository.save(user);

            send(user.getEmail(), "Bookstore Verification Email", "Your verification code is " + user.getVerificationCode() + ".\nThis verification code will expire in 5 minutes.");
        }
    }

    public String generateVerificationCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100000, 999999));
    }
}
