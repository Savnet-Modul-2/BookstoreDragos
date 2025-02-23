package com.example.Spring_Initializr_Bookstore.mapper;

import com.example.Spring_Initializr_Bookstore.entities.Library;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.LibraryDTO;

public class LibraryMapper {
    public static Library libraryDTO2Library(LibraryDTO libraryDTO) {
        Library library = new Library();

        library.setId(libraryDTO.getId());
        library.setName(libraryDTO.getName());
        library.setAddress(libraryDTO.getAddress());
        library.setPhoneNumber(libraryDTO.getPhoneNumber());
/*
        if (libraryDTO.getLibrarianDTO() != null) {
            library.setLibrarian(LibrarianMapper.librarianDTO2Librarian(libraryDTO.getLibrarianDTO()));
        }
*/
        if (libraryDTO.getBookDTOS() != null && !libraryDTO.getBookDTOS().isEmpty()) {
            library.setBooks(libraryDTO.getBookDTOS().stream().map(BookMapper::bookDTO2Book).toList());
        }

        return library;
    }

    public static LibraryDTO library2LibraryDTO(Library library) {
        LibraryDTO libraryDTO = new LibraryDTO();

        libraryDTO.setId(library.getId());
        libraryDTO.setName(library.getName());
        libraryDTO.setAddress(library.getAddress());
        libraryDTO.setPhoneNumber(library.getPhoneNumber());
/*
        if (library.getLibrarian() != null) {
            libraryDTO.setLibrarianDTO(LibrarianMapper.librarian2LibrarianDTO(library.getLibrarian()));
        }
*/
        if (library.getBooks() != null && !library.getBooks().isEmpty()) {
            libraryDTO.setBookDTOS(library.getBooks().stream().map(BookMapper::book2BookDTO).toList());
        }

        return libraryDTO;
    }
}
