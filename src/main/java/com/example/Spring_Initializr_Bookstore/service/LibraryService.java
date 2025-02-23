package com.example.Spring_Initializr_Bookstore.service;

import com.example.Spring_Initializr_Bookstore.entities.Library;
import com.example.Spring_Initializr_Bookstore.repositories.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class LibraryService {
    @Autowired()
    private LibraryRepository libraryRepository;

    public Library create(Library libraryToCreate) {
        return libraryRepository.save(libraryToCreate);
    }

    public Library findByID(Long libraryID) {
        return libraryRepository.findById(libraryID).orElseThrow(() -> new EntityNotFoundException("Library with ID " + libraryID + " not found."));
    }
}
