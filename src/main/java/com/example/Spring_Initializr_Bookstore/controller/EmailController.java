package com.example.Spring_Initializr_Bookstore.controller;

import com.example.Spring_Initializr_Bookstore.entities.User;
import com.example.Spring_Initializr_Bookstore.mapper.UserMapper;
import com.example.Spring_Initializr_Bookstore.repositories.UserRepository;
import com.example.Spring_Initializr_Bookstore.service.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/email")
public class EmailController {
    @Autowired()
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/send/{userID}")
    public ResponseEntity<?> sendEmail(@PathVariable(name = "userID") Long userID, @RequestParam() String subject, @RequestParam() String text) {
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + "not found."));

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " does not have an email address.");
        }

        String message = "\nEmail sent to user with ID " + userID + ".";

        emailService.send(user.getEmail(), subject, text);

        return ResponseEntity.ok(UserMapper.user2UserDTO(user) + message);
    }

    @PostMapping(path = "/send-verification/{userID}")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable(name = "userID") Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + "not found."));

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " does not have an email address.");
        }

        String message = "\nVerification email sent to user with ID " + userID + ".";

        emailService.sendVerification(user);

        return ResponseEntity.ok(UserMapper.user2UserDTO(user) + message);
    }
}
