package com.example.SpringBookstore.mapper;

import com.example.SpringBookstore.entity.Librarian;
import com.example.SpringBookstore.entityDTO.LibrarianDTO;

public class LibrarianMapper {
    public static Librarian librarianDTO2Librarian(LibrarianDTO librarianDTO) {
        Librarian librarian = new Librarian();

        librarian.setID(librarianDTO.getID());
        librarian.setName(librarianDTO.getName());
        librarian.setEmail(librarianDTO.getEmailAddress());
        librarian.setPassword(librarianDTO.getPassword());
        librarian.setVerifiedAccount(librarianDTO.getVerifiedAccount());

        if (librarianDTO.getLibraryDTO() != null) {
            librarian.setLibrary(LibraryMapper.libraryDTO2Library(librarianDTO.getLibraryDTO()));
        }

        return librarian;
    }

    public static LibrarianDTO librarian2LibrarianDTO(Librarian librarian) {
        LibrarianDTO librarianDTO = new LibrarianDTO();

        librarianDTO.setID(librarian.getID());
        librarianDTO.setName(librarian.getName());
        librarianDTO.setEmailAddress(librarian.getEmail());
        librarianDTO.setPassword(librarian.getPassword());
        librarianDTO.setVerifiedAccount(librarian.getVerifiedAccount());

        return librarianDTO;
    }
}
