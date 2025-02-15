package com.example.Spring_Initializr_Bookstore.service;

import com.example.Spring_Initializr_Bookstore.entities.User;
import com.example.Spring_Initializr_Bookstore.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class UserService {
    @Autowired()
    private UserRepository userRepository;

    public User create(User user) {
        if (user.getId() != null) throw new RuntimeException("Cannot provide an ID when creating a new user.");
        return userRepository.save(user);
    }

    public User findByID(Long userID) {
        return userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + "not found."));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
