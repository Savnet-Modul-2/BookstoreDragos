package com.example.Spring_Initializr_Bookstore.repositories;

import com.example.Spring_Initializr_Bookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository()
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String emailAddress);
}
