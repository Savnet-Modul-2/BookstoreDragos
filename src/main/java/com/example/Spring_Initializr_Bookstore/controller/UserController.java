package com.example.Spring_Initializr_Bookstore.controller;

import com.example.Spring_Initializr_Bookstore.entities.User;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.UserDTO;
import com.example.Spring_Initializr_Bookstore.mapper.UserMapper;
import com.example.Spring_Initializr_Bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/users")
public class UserController {
    @Autowired()
    private UserService userService;

    @PostMapping(path = "/create-user")
    public ResponseEntity<?> create(@RequestBody() UserDTO userDTO) {
        User userToCreate = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(userToCreate);

        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }

    @GetMapping(path = "/find-user-by-id/{userID}")
    public ResponseEntity<?> findByID(@PathVariable(name = "userID") Long userID) {
        User foundUser = userService.findByID(userID);

        return ResponseEntity.ok(UserMapper.user2UserDTO(foundUser));
    }

    @GetMapping(path = "/find-all-users")
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOS = users.stream().map(UserMapper::user2UserDTO).toList();

        return ResponseEntity.ok(userDTOS);
    }
}
