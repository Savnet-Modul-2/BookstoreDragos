package com.example.SpringBookstore.dto;

public class LibraryDTO {
    private Long id;
    private String name;
    private String address;
    private LibrarianDTO librarianDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LibrarianDTO getLibrarianDTO() {
        return librarianDTO;
    }

    public void setLibrarianDTO(LibrarianDTO librarianDTO) {
        this.librarianDTO = librarianDTO;
    }
}
