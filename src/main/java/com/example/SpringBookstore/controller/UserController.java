package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entity.User;
import com.example.SpringBookstore.entityDTO.UserDTO;
import com.example.SpringBookstore.entityDTO.validation.ValidationOrder;
import com.example.SpringBookstore.mapper.UserMapper;
import com.example.SpringBookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated(value = ValidationOrder.class) @RequestBody UserDTO userDTO) {
        User userToCreate = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(userToCreate);

        userService.sendVerificationCode(createdUser);

        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }

    @GetMapping(path = "/{userID}")
    public ResponseEntity<?> findByID(@PathVariable(name = "userID") Long userID) {
        User foundUser = userService.findByID(userID);
        return ResponseEntity.ok(UserMapper.user2UserDTO(foundUser));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<User> foundUsers = userService.findAll();

        return ResponseEntity.ok(foundUsers.stream()
                .map(UserMapper::user2UserDTO)
                .toList());
    }

    @PutMapping(path = "/{userID}")
    public ResponseEntity<?> update(@PathVariable(name = "userID") Long userID, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.update(userID, userDTO);
        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }

    @DeleteMapping(path = "/{userID}")
    public ResponseEntity<?> delete(@PathVariable(name = "userID") Long userID) {
        userService.delete(userID);
        return ResponseEntity.ok().body("Deleted user with ID " + userID + ".");
    }

    @PostMapping(path = "/{userID}")
    public ResponseEntity<?> sendEmail(@PathVariable(name = "userID") Long userID, @RequestParam String subject, @RequestParam String text) {
        User user = userService.checkEmail(userID);

        userService.sendEmail(user.getEmail(), subject, text);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/send-verification/{userID}")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable(name = "userID") Long userID) {
        User user = userService.checkEmail(userID);

        userService.sendVerificationCode(user);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/resend-verification/{userID}")
    public ResponseEntity<?> resendVerificationEmail(@PathVariable(name = "userID") Long userID) {
        User user = userService.checkEmail(userID);

        userService.resendVerificationCode(user);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/verification")
    public ResponseEntity<?> verifyCode(@RequestParam Long userID, @RequestParam String code) {
        User user = userService.checkVerificationCode(userID, code);
        return ResponseEntity.ok(UserMapper.user2UserDTO(user));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestParam String emailAddress, @RequestParam String password) {
        User user = userService.login(emailAddress, password);
        return ResponseEntity.ok(UserMapper.user2UserDTO(user));
    }
}
