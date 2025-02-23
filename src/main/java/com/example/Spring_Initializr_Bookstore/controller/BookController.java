package com.example.Spring_Initializr_Bookstore.controller;

import com.example.Spring_Initializr_Bookstore.entities.Book;
import com.example.Spring_Initializr_Bookstore.entities.Library;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.BookDTO;
import com.example.Spring_Initializr_Bookstore.mapper.BookMapper;
import com.example.Spring_Initializr_Bookstore.service.BookService;
import com.example.Spring_Initializr_Bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/books")
public class BookController {
    @Autowired()
    private BookService bookService;
    @Autowired()
    private LibraryService libraryService;

    @PostMapping(path = "/create-book")
    public ResponseEntity<?> create(@RequestBody BookDTO bookDTO) {
        Book bookToCreate = BookMapper.bookDTO2Book(bookDTO);
        Book createdBook = bookService.create(bookToCreate);

        return ResponseEntity.ok(BookMapper.book2BookDTO(createdBook));
    }

    @GetMapping(path = "/find-book/{bookID}")
    public ResponseEntity<?> findByID(@PathVariable(name = "bookID") Long bookID) {
        Book foundBook = bookService.findByID(bookID);

        return ResponseEntity.ok(BookMapper.book2BookDTO(foundBook));
    }

    @GetMapping(path = "/list-all-books")
    public ResponseEntity<?> listAll() {
        List<Book> books = bookService.listAll();
        List<BookDTO> bookDTOS = books.stream().map(BookMapper::book2BookDTO).toList();

        return ResponseEntity.ok(bookDTOS);
    }

    @GetMapping(path = "/list-books-paginated")
    public ResponseEntity<?> listPaginated(@RequestParam() Integer page, @RequestParam() Integer numberOfElements) {
        Page<Book> books = bookService.listPaginated(page, numberOfElements);
        List<BookDTO> bookDTOS = books.stream().map(BookMapper::book2BookDTO).toList();

        return ResponseEntity.ok(bookDTOS);
    }

    @PutMapping(path = "/update-book/{bookID}")
    public ResponseEntity<?> update(@PathVariable(name = "bookID") Long bookID, @RequestBody() BookDTO bookDTO) {
        Book bookToUpdate = bookService.findByID(bookID);
        Book updatedBook = bookService.update(bookToUpdate, bookDTO);

        return ResponseEntity.ok((BookMapper.book2BookDTO(updatedBook)));
    }

    @PutMapping(path = "/add/{bookID}/to/{libraryID}")
    public ResponseEntity<?> addBook(@PathVariable(name = "bookID") Long bookID, @PathVariable(name = "libraryID") Long libraryID) {
        Book bookToAdd = bookService.findByID(bookID);
        Library libraryToReceive = libraryService.findByID(libraryID);

        bookService.addBook(bookToAdd, libraryToReceive);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/remove/{bookID}/from/{libraryID}")
    public ResponseEntity<?> removeBook(@PathVariable(name = "bookID") Long bookID, @PathVariable(name = "libraryID") Long libraryID) {
        Book bookToRemove = bookService.findByID(bookID);
        Library libraryToDiscard = libraryService.findByID(libraryID);

        bookService.removeBook(bookToRemove, libraryToDiscard);

        return ResponseEntity.noContent().build();
    }
}
