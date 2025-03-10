package com.example.SpringBookstore.mappers;

import com.example.SpringBookstore.entities.Library;
import com.example.SpringBookstore.entitiesDTO.LibraryDTO;

public class LibraryMapper {
    public static Library libraryDTO2Library(LibraryDTO libraryDTO) {
        Library library = new Library();

        library.setID(libraryDTO.getID());
        library.setName(libraryDTO.getName());
        library.setAddress(libraryDTO.getAddress());
        library.setPhoneNumber(libraryDTO.getPhoneNumber());

        if (libraryDTO.getBookDTOS() != null) {
            library.setBooks(libraryDTO.getBookDTOS().stream()
                    .map(BookMapper::bookDTO2Book)
                    .toList());
        }

        return library;
    }

    public static LibraryDTO library2LibraryDTO(Library library) {
        LibraryDTO libraryDTO = new LibraryDTO();

        libraryDTO.setID(library.getID());
        libraryDTO.setName(library.getName());
        libraryDTO.setAddress(library.getAddress());
        libraryDTO.setPhoneNumber(library.getPhoneNumber());

        return libraryDTO;
    }
}
