package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entity.Library;
import com.example.SpringBookstore.entityDTO.LibraryDTO;
import com.example.SpringBookstore.entityDTO.validation.information.ValidationOrder;
import com.example.SpringBookstore.mapper.LibraryMapper;
import com.example.SpringBookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/libraries")
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated(value = ValidationOrder.class) @RequestBody LibraryDTO libraryDTO) {
        Library libraryToCreate = LibraryMapper.libraryDTO2Library(libraryDTO);
        Library createdLibrary = libraryService.create(libraryToCreate);

        return ResponseEntity.ok(LibraryMapper.library2LibraryDTO(createdLibrary));
    }

    @GetMapping(path = "/{libraryID}")
    public ResponseEntity<?> findByID(@PathVariable(name = "libraryID") Long libraryID) {
        Library foundLibrary = libraryService.findByID(libraryID);
        return ResponseEntity.ok(LibraryMapper.library2LibraryDTO(foundLibrary));
    }
}
