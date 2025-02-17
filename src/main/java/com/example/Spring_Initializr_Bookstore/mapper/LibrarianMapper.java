package com.example.Spring_Initializr_Bookstore.mapper;

import com.example.Spring_Initializr_Bookstore.entities.Librarian;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.LibrarianDTO;

public class LibrarianMapper {
    public static Librarian librarianDTO2Librarian(LibrarianDTO bookDTO) {
        Librarian book = new Librarian();

        book.setId(bookDTO.getId());

        return book;
    }

    public static LibrarianDTO librarian2LibrarianDTO(LibrarianDTO book) {
        LibrarianDTO bookDTO = new LibrarianDTO();

        bookDTO.setId(book.getId());

        return bookDTO;
    }
}
