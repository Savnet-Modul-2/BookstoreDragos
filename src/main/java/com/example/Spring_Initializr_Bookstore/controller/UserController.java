package com.example.Spring_Initializr_Bookstore.controller;

import com.example.Spring_Initializr_Bookstore.entities.User;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.UserDTO;
import com.example.Spring_Initializr_Bookstore.mapper.UserMapper;
import com.example.Spring_Initializr_Bookstore.repositories.UserRepository;
import com.example.Spring_Initializr_Bookstore.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/users")
public class UserController {
    @Autowired()
    private UserRepository userRepository;
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
        List<User> foundUsers = userService.findAll();
        List<UserDTO> userDTOS = foundUsers.stream().map(UserMapper::user2UserDTO).toList();

        return ResponseEntity.ok(userDTOS);
    }

    @PutMapping(path = "/update-user/{userID}")
    public ResponseEntity<?> update(@PathVariable(name = "userID") Long userID, @RequestBody UserDTO userDTO) {
        User userToUpdate = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + " not found."));
        User updatedUser = userService.update(userToUpdate, userDTO);

        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }

    @DeleteMapping(path = "/delete-user/{userID}")
    public ResponseEntity<?> delete(@PathVariable(name = "userID") Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + " not found."));
        userService.delete(user);

        return ResponseEntity.ok().body("Deleted user with ID " + userID + ".");
    }
}
