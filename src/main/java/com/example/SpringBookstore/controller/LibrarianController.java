package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entities.Librarian;
import com.example.SpringBookstore.entitiesDTO.LibrarianDTO;
import com.example.SpringBookstore.mapper.LibrarianMapper;
import com.example.SpringBookstore.service.LibrarianService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/librarians")
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LibrarianDTO librarianDTO) {
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

        List<LibrarianDTO> librarianDTOS = librarians.stream()
                .map(LibrarianMapper::librarian2LibrarianDTO)
                .toList();

        return ResponseEntity.ok(librarianDTOS);
    }

    @DeleteMapping(path = "/{librarianID}")
    public ResponseEntity<?> delete(@PathVariable(name = "librarianID") Long librarianID) {
        librarianService.delete(librarianID);
        return ResponseEntity.ok().body("Deleted librarian with ID " + librarianID + ".");
    }

    @PostMapping(path = "/{librarianID}")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable(name = "librarianID") Long librarianID) {
        Librarian librarian = librarianService.checkEmail(librarianID);

        librarianService.sendVerificationCode(librarian);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/verification")
    public ResponseEntity<?> verifyAccount(@RequestParam Long librarianID, @RequestParam String code) {
        Librarian librarian = librarianService.checkVerificationCode(librarianID, code);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(librarian));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestParam String emailAddress, @RequestParam String password) throws BadRequestException {
        Librarian librarian = librarianService.login(emailAddress, password);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(librarian));
    }
}
