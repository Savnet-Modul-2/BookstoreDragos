package com.example.SpringBookstore.entity;

import com.example.SpringBookstore.BookCategory;
import com.example.SpringBookstore.exceptionHandling.exception.BadRequestException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "book")
@Table(name = "books", schema = "public")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "CATEGORY")
    @Enumerated(value = EnumType.STRING)
    private BookCategory category;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "NUMBER_OF_PAGES")
    private Integer numberOfPages;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LIBRARY_ID")
    private Library library;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "book", orphanRemoval = true)
    private List<Copy> copies = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    public void addCopy(Copy copy) {
        if (!copies.contains(copy)) {
            copies.add(copy);
            copy.setBook(this);
        } else {
            throw new BadRequestException("Book already contains copy with ID " + copy.getId() + ".");
        }
    }

    public void removeCopy(Copy copy) {
        if (copies.contains(copy)) {
            copies.remove(copy);
            copy.setBook(null);
        } else {
            throw new BadRequestException("Book does not contain copy with ID " + copy.getId() + ".");
        }
    }
}
