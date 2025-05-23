package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entity.Book;
import com.example.SpringBookstore.entityDTO.BookDTO;
import com.example.SpringBookstore.entityDTO.validation.information.ValidationOrder;
import com.example.SpringBookstore.mapper.BookMapper;
import com.example.SpringBookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated(value = ValidationOrder.class) @RequestBody BookDTO bookDTO) {
        Book bookToCreate = BookMapper.bookDTO2Book(bookDTO);
        Book createdBook = bookService.create(bookToCreate);

        return ResponseEntity.ok(BookMapper.book2BookDTO(createdBook));
    }

    @GetMapping(path = "/{bookID}")
    public ResponseEntity<?> findByID(@PathVariable(name = "bookID") Long bookID) {
        Book foundBook = bookService.findByID(bookID);
        return ResponseEntity.ok(BookMapper.book2BookDTO(foundBook));
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<Book> books = bookService.listAll();

        return ResponseEntity.ok(books.stream()
                .map(BookMapper::book2BookDTO)
                .toList());
    }

    @GetMapping(path = "/paginated")
    public ResponseEntity<?> listPaginated(@RequestParam(required = false) Integer pageNumber,
                                           @RequestParam(required = false) Integer pageSize) {
        Page<Book> books = bookService.listPaginated(pageNumber, pageSize);

        return ResponseEntity.ok(books.stream()
                .map(BookMapper::book2BookDTO)
                .toList());
    }

    @PutMapping(path = "/{bookID}")
    public ResponseEntity<?> update(@PathVariable(name = "bookID") Long bookID,
                                    @Validated(value = ValidationOrder.class) @RequestBody BookDTO bookDTO) {
        Book updatedBook = bookService.update(bookID, bookDTO);
        return ResponseEntity.ok((BookMapper.book2BookDTO(updatedBook)));
    }

    @DeleteMapping(path = "/{bookID}")
    public ResponseEntity<?> delete(@PathVariable(name = "bookID") Long bookID) {
        bookService.delete(bookID);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{bookID}/{libraryID}")
    public ResponseEntity<?> addBook(@PathVariable(name = "bookID") Long bookID,
                                     @PathVariable(name = "libraryID") Long libraryID) {
        bookService.addBook(bookID, libraryID);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{bookID}/{libraryID}")
    public ResponseEntity<?> removeBook(@PathVariable(name = "bookID") Long bookID,
                                        @PathVariable(name = "libraryID") Long libraryID) {
        bookService.removeBook(bookID, libraryID);
        return ResponseEntity.noContent().build();
    }
}
