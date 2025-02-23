package com.example.Spring_Initializr_Bookstore.controller;

import com.example.Spring_Initializr_Bookstore.entities.Library;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.LibraryDTO;
import com.example.Spring_Initializr_Bookstore.mapper.LibraryMapper;
import com.example.Spring_Initializr_Bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/libraries")
public class LibraryController {
    @Autowired()
    private LibraryService libraryService;

    @PostMapping(path = "/create-library")
    public ResponseEntity<?> create(@RequestBody() LibraryDTO libraryDTO) {
        Library libraryToCreate = LibraryMapper.libraryDTO2Library(libraryDTO);
        Library createdLibrary = libraryService.create(libraryToCreate);

        return ResponseEntity.ok(LibraryMapper.library2LibraryDTO(createdLibrary));
    }

    @GetMapping(path = "/find-library/{libraryID}")
    public ResponseEntity<?> findByID(@PathVariable(name = "libraryID") Long libraryID) {
        Library foundLibrary = libraryService.findByID(libraryID);
        return ResponseEntity.ok(LibraryMapper.library2LibraryDTO(foundLibrary));
    }
}
