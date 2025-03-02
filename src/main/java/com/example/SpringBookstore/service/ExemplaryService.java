package com.example.SpringBookstore.service;

import com.example.SpringBookstore.entities.Book;
import com.example.SpringBookstore.entities.Exemplary;
import com.example.SpringBookstore.repositories.BookRepository;
import com.example.SpringBookstore.repositories.ExemplaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemplaryService {
    private final ExemplaryRepository exemplaryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ExemplaryService(ExemplaryRepository exemplaryRepository, BookRepository bookRepository) {
        this.exemplaryRepository = exemplaryRepository;
        this.bookRepository = bookRepository;
    }

    public List<Exemplary> create(List<Exemplary> exemplarsToCreate, Long bookID) {
        Book book = bookRepository.findById(bookID)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookID + " not found."));

        exemplarsToCreate.forEach(exemplary -> {
            book.addExemplary(exemplary);
            exemplaryRepository.save(exemplary);
        });

        return exemplarsToCreate;
    }

    public Page<Exemplary> listPaginated(Long bookID, Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageSize != null) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            return exemplaryRepository.findByBookId(bookID, pageable);
        }

        return exemplaryRepository.findByBookId(bookID, Pageable.unpaged());
    }

    public void delete(Long exemplaryID) {
        if (!exemplaryRepository.existsById(exemplaryID)) {
            throw new EntityNotFoundException("Exemplary with ID " + exemplaryID + " not found.");
        }

        exemplaryRepository.deleteById(exemplaryID);
    }
}
