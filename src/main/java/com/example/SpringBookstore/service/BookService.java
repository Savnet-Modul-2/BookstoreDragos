package com.example.SpringBookstore.service;

import com.example.SpringBookstore.entities.Book;
import com.example.SpringBookstore.entities.Library;
import com.example.SpringBookstore.entitiesDTO.BookDTO;
import com.example.SpringBookstore.mapper.LibraryMapper;
import com.example.SpringBookstore.repositories.BookRepository;
import com.example.SpringBookstore.repositories.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private LibraryService libraryService;

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book findByID(Long bookID) {
        return bookRepository.findById(bookID)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookID + " not found."));
    }

    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    public Page<Book> listPaginated(Integer pageNumber, Integer numberOfElements) {
        if (pageNumber != null && numberOfElements != null) {
            Pageable pageable = PageRequest.of(pageNumber, numberOfElements);
            return bookRepository.findAll(pageable);
        }

        return bookRepository.findAll(Pageable.unpaged());
    }

    public Book update(Long bookID, BookDTO bookUpdate) {
        Book bookToUpdate = findByID(bookID);

        bookToUpdate.setIsbn(bookUpdate.getIsbn());
        bookToUpdate.setTitle(bookUpdate.getTitle());
        bookToUpdate.setAuthor(bookUpdate.getAuthor());
        bookToUpdate.setReleaseDate(bookUpdate.getReleaseDate());
        bookToUpdate.setNumberOfPages(bookUpdate.getNumberOfPages());
        bookToUpdate.setCategory(bookUpdate.getCategory());
        bookToUpdate.setLanguage(bookUpdate.getLanguage());

        if (bookUpdate.getLibraryDTO() != null) {
            bookToUpdate.setLibrary(LibraryMapper.libraryDTO2Library(bookUpdate.getLibraryDTO()));
        }

        return bookRepository.save(bookToUpdate);
    }

    public void addBook(Long bookID, Long libraryID) {
        Book bookToAdd = findByID(bookID);
        Library libraryToReceive = libraryService.findByID(libraryID);

        libraryToReceive.addBook(bookToAdd);

        libraryRepository.save(libraryToReceive);
    }

    public void removeBook(Long bookID, Long libraryID) {
        Book bookToRemove = findByID(bookID);
        Library libraryToDiscard = libraryService.findByID(libraryID);

        libraryToDiscard.removeBook(bookToRemove);

        libraryRepository.save(libraryToDiscard);
    }
}
