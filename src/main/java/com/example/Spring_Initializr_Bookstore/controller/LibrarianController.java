package com.example.Spring_Initializr_Bookstore.controller;

import com.example.Spring_Initializr_Bookstore.entities.Librarian;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.LibrarianDTO;
import com.example.Spring_Initializr_Bookstore.mapper.LibrarianMapper;
import com.example.Spring_Initializr_Bookstore.repositories.LibrarianRepository;
import com.example.Spring_Initializr_Bookstore.service.LibrarianService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/librarians")
public class LibrarianController {
    @Autowired()
    private LibrarianRepository librarianRepository;
    @Autowired()
    private LibrarianService librarianService;

    @PostMapping(path = "/create-librarian")
    public ResponseEntity<?> create(@RequestBody() LibrarianDTO librarianDTO) {
        Librarian librarianToCreate = LibrarianMapper.librarianDTO2Librarian(librarianDTO);
        Librarian createdLibrarian = librarianService.create(librarianToCreate);

        librarianService.sendVerificationCode(createdLibrarian);

        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(createdLibrarian));
    }

    @PostMapping(path = "/send-verification/{librarianID}")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable(name = "librarianID") Long librarianID) {
        Librarian librarian = librarianService.checkLibrarian(librarianID);

        librarianService.sendVerificationCode(librarian);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/verify-code")
    public ResponseEntity<?> verifyAccount(@RequestParam() Long librarianID, @RequestParam() String code) {
        Librarian librarian = librarianRepository.findById(librarianID).orElseThrow(() -> new EntityNotFoundException("User with ID " + librarianID + " not found."));
        String message = librarianService.checkVerificationCode(librarian, code);

        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(librarian) + message);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestParam() String emailAddress, @RequestParam String password) {
        Librarian librarian = librarianRepository.findByEmail(emailAddress).orElseThrow(() -> new EntityNotFoundException("Librarian with email address " + emailAddress + " not found."));
        Boolean loginValidation = librarianService.login(librarian, emailAddress, password);

        if (loginValidation) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body("Login attempt unsuccessful. Invalid email address or password.");
        }
    }
}
