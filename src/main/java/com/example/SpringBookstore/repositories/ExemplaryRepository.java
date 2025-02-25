package com.example.SpringBookstore.repositories;

import com.example.SpringBookstore.entities.Exemplary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaryRepository extends JpaRepository<Exemplary, Long> {
}
