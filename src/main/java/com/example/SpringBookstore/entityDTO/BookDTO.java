package com.example.SpringBookstore.entityDTO;

import com.example.SpringBookstore.BookCategory;
import com.example.SpringBookstore.entityDTO.validation.information.AdvancedInformation;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidBookInformation;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidBookNumberOfPages;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidBookReleaseDateNotInTheFuture;

import java.time.LocalDate;

@ValidBookInformation(groups = AdvancedInformation.class)
@ValidBookNumberOfPages(groups = AdvancedInformation.class)
@ValidBookReleaseDateNotInTheFuture(groups = AdvancedInformation.class)
public class BookDTO {
    private Long id;
    private Long isbn;
    private String title;
    private String author;
    private BookCategory bookCategory;
    private String language;
    private Integer numberOfPages;
    private LocalDate releaseDate;
    private LibraryDTO libraryDTO;

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Long getISBN() {
        return isbn;
    }

    public void setISBN(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return bookCategory;
    }

    public void setCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LibraryDTO getLibraryDTO() {
        return libraryDTO;
    }

    public void setLibraryDTO(LibraryDTO libraryDTO) {
        this.libraryDTO = libraryDTO;
    }
}
