package com.example.Spring_Initializr_Bookstore.controller;

import com.example.Spring_Initializr_Bookstore.entities.User;
import com.example.Spring_Initializr_Bookstore.repositories.UserRepository;
import com.example.Spring_Initializr_Bookstore.service.EmailService;
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
        User user = emailService.checkUser(userID);

        emailService.send(user.getEmail(), subject, text);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/send-verification/{userID}")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable(name = "userID") Long userID) {
        User user = emailService.checkUser(userID);

        emailService.sendVerification(user);

        return ResponseEntity.noContent().build();
    }
}
