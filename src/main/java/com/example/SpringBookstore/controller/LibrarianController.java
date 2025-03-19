package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entity.Librarian;
import com.example.SpringBookstore.entityDTO.LibrarianDTO;
import com.example.SpringBookstore.entityDTO.validation.ValidationOrder;
import com.example.SpringBookstore.mapper.LibrarianMapper;
import com.example.SpringBookstore.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/librarians")
public class LibrarianController {
    private final LibrarianService librarianService;

    @Autowired
    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated(value = ValidationOrder.class) @RequestBody LibrarianDTO librarianDTO) {
        Librarian librarianToCreate = LibrarianMapper.librarianDTO2Librarian(librarianDTO);
        Librarian createdLibrarian = librarianService.create(librarianToCreate);

        librarianService.sendVerificationCode(createdLibrarian);

        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(createdLibrarian));
    }

    @GetMapping(path = "/{librarianID}")
    public ResponseEntity<?> findByID(@PathVariable(name = "librarianID") Long librarianID) {
        Librarian foundLibrarian = librarianService.findByID(librarianID);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(foundLibrarian));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Librarian> librarians = librarianService.findAll();

        return ResponseEntity.ok(librarians.stream()
                .map(LibrarianMapper::librarian2LibrarianDTO)
                .toList());
    }

    @DeleteMapping(path = "/{librarianID}")
    public ResponseEntity<?> delete(@PathVariable(name = "librarianID") Long librarianID) {
        librarianService.delete(librarianID);
        return ResponseEntity.ok().body("Deleted librarian with ID " + librarianID + ".");
    }

    @PostMapping(path = "/{librarianID}")
    public ResponseEntity<?> sendEmail(@PathVariable(name = "librarianID") Long librarianID, @RequestParam String subject, @RequestParam String text) {
        Librarian librarian = librarianService.checkEmail(librarianID);

        librarianService.sendEmail(librarian.getEmail(), subject, text);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/send-verification/{librarianID}")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable(name = "librarianID") Long librarianID) {
        Librarian librarian = librarianService.checkEmail(librarianID);

        librarianService.sendVerificationCode(librarian);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/resend-verification/{librarianID}")
    public ResponseEntity<?> resendVerificationEmail(@PathVariable(name = "librarianID") Long librarianID) {
        Librarian librarian = librarianService.checkEmail(librarianID);

        librarianService.resendVerificationCode(librarian);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/verification")
    public ResponseEntity<?> verifyAccount(@RequestParam Long librarianID, @RequestParam String code) {
        Librarian librarian = librarianService.checkVerificationCode(librarianID, code);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(librarian));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestParam String emailAddress, @RequestParam String password) {
        Librarian librarian = librarianService.login(emailAddress, password);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(librarian));
    }
}
