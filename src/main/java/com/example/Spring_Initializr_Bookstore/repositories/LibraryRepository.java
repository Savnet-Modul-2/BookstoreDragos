package com.example.Spring_Initializr_Bookstore.repositories;

import com.example.Spring_Initializr_Bookstore.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface LibraryRepository extends JpaRepository<Library, Long> {
}
