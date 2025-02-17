package com.example.Spring_Initializr_Bookstore.mapper;

import com.example.Spring_Initializr_Bookstore.entities.Library;
import com.example.Spring_Initializr_Bookstore.entitiesDTO.LibraryDTO;

public class LibraryMapper {
    public static Library libraryDTO2Library(LibraryDTO libraryDTO) {
        Library library = new Library();

        library.setId(libraryDTO.getId());

        return library;
    }

    public static LibraryDTO library2LibraryDTO(Library library) {
        LibraryDTO libraryDTO = new LibraryDTO();

        libraryDTO.setId(library.getId());

        return libraryDTO;
    }
}
