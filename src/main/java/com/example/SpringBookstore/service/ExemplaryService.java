package com.example.SpringBookstore.service;

import com.example.SpringBookstore.entities.Book;
import com.example.SpringBookstore.entities.Exemplary;
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
    private final BookService bookService;

    @Autowired
    public ExemplaryService(ExemplaryRepository exemplaryRepository, BookService bookService) {
        this.exemplaryRepository = exemplaryRepository;
        this.bookService = bookService;
    }

    public List<Exemplary> create(List<Exemplary> exemplarsToCreate, Long bookID) {
        Book book = bookService.findByID(bookID);

        exemplarsToCreate.forEach(exemplary -> {
            exemplary.setBook(book);
            exemplaryRepository.save(exemplary);
        });

        return exemplarsToCreate;
    }

    public Page<Exemplary> listPaginated(Integer pageNumber, Integer numberOfElements) {
        if (pageNumber != null && numberOfElements != null) {
            Pageable pageable = PageRequest.of(pageNumber, numberOfElements);
            return exemplaryRepository.findAll(pageable);
        }

        return exemplaryRepository.findAll(Pageable.unpaged());
    }

    public void delete(Long exemplaryID) {
        if (exemplaryRepository.existsById(exemplaryID)) {
            exemplaryRepository.deleteById(exemplaryID);
        } else {
            throw new EntityNotFoundException("Exemplary with ID " + exemplaryID + " not found.");
        }
    }
}
