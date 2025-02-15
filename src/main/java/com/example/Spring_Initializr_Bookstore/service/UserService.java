package com.example.Spring_Initializr_Bookstore.service;

import com.example.Spring_Initializr_Bookstore.entities.User;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.UserDTO;
import com.example.Spring_Initializr_Bookstore.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service()
public class UserService {
    @Autowired()
    private UserRepository userRepository;

    public User create(User user) {
        if (user.getId() != null) throw new RuntimeException("Cannot provide an ID when creating a new user.");
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        return userRepository.save(user);
    }

    public User findByID(Long userID) {
        return userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + "not found."));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(User userToUpdate, UserDTO userUpdate) {
        userToUpdate.setFirstName(userUpdate.getFirstName());
        userToUpdate.setLastName(userUpdate.getLastName());
        userToUpdate.setGender(userUpdate.getGender());
        userToUpdate.setCountry(userUpdate.getCountry());
        userToUpdate.setYearOfBirth(userUpdate.getYearOfBirth());
        userToUpdate.setEmail(userUpdate.getEmail());
        userToUpdate.setPhoneNumber(userUpdate.getPhoneNumber());

        return userRepository.save(userToUpdate);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
