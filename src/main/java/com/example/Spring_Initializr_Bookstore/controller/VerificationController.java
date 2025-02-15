package com.example.Spring_Initializr_Bookstore.controller;

import com.example.Spring_Initializr_Bookstore.entities.User;
import com.example.Spring_Initializr_Bookstore.mapper.UserMapper;
import com.example.Spring_Initializr_Bookstore.repositories.UserRepository;
import com.example.Spring_Initializr_Bookstore.service.VerificationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/verification")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/code")
    public ResponseEntity<?> verifyCode(@RequestParam() Long userID, @RequestParam() String code) {
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + " not found."));
        String message = verificationService.verifyCode(user, code);

        return ResponseEntity.ok(UserMapper.user2UserDTO(user) + message);
    }
}
