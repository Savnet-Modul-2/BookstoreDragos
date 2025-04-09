package com.example.SpringBookstore.mapper;

import com.example.SpringBookstore.dto.BookDTO;
import com.example.SpringBookstore.entity.Book;

public class BookMapper {
    public static Book bookDTO2Book(BookDTO bookDTO) {
        Book book = new Book();

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setLanguage(bookDTO.getLanguage());
        book.setNumberOfPages(bookDTO.getNumberOfPages());

        return book;
    }

    public static BookDTO book2BookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setLanguage(book.getLanguage());
        bookDTO.setNumberOfPages(book.getNumberOfPages());

        return bookDTO;
    }
}
