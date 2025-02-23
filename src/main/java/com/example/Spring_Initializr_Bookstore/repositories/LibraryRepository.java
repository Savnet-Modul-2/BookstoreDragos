package com.example.Spring_Initializr_Bookstore.repositories;

import com.example.Spring_Initializr_Bookstore.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface LibraryRepository extends JpaRepository<Library, Long> {
}
