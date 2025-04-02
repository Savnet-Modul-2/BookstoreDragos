package com.example.SpringBookstore.unitTest.entity;

import com.example.SpringBookstore.BookCategory;
import com.example.SpringBookstore.entity.Book;
import com.example.SpringBookstore.entity.Exemplary;
import com.example.SpringBookstore.entity.Library;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookTests {
    private Book testBook;

    @BeforeEach
    public void setUp() {
        testBook = new Book();
    }

    @Test
    public void givenBook_getID_returnNotNull() {
        testBook.setID(1L);
        Assertions.assertThat(testBook.getID()).isNotNull();
    }

    @Test
    public void givenNothing_getID_returnNothing() {
        testBook.setID(null);
        Assertions.assertThat(testBook.getID()).isNull();
    }

    @Test
    public void givenBook_getISBN_returnNotNull() {
        testBook.setISBN(1234L);
        Assertions.assertThat(testBook.getISBN()).isNotNull();
    }

    @Test
    public void givenNothing_getISBN_returnNothing() {
        testBook.setISBN(null);
        Assertions.assertThat(testBook.getISBN()).isNull();
    }

    @Test
    public void givenBook_getTitle_returnNotNull() {
        testBook.setTitle("Test Title");
        Assertions.assertThat(testBook.getTitle()).isNotNull();
    }

    @Test
    public void givenNothing_getTitle_returnNothing() {
        testBook.setTitle(null);
        Assertions.assertThat(testBook.getTitle()).isNull();
    }

    @Test
    public void givenBook_getAuthor_returnNotNull() {
        testBook.setAuthor("Test Author");
        Assertions.assertThat(testBook.getAuthor()).isNotNull();
    }

    @Test
    public void givenNothing_getAuthor_returnNothing() {
        testBook.setAuthor(null);
        Assertions.assertThat(testBook.getAuthor()).isNull();
    }

    @Test
    public void givenBook_getCategory_returnNotNull() {
        testBook.setCategory(BookCategory.ACTION);
        Assertions.assertThat(testBook.getCategory()).isNotNull();
    }

    @Test
    public void givenNothing_getCategory_returnNothing() {
        testBook.setCategory(null);
        Assertions.assertThat(testBook.getCategory()).isNull();
    }

    @Test
    public void givenBook_getLanguage_returnNotNull() {
        testBook.setLanguage("Test Language");
        Assertions.assertThat(testBook.getLanguage()).isNotNull();
    }

    @Test
    public void givenNothing_getLanguage_returnNothing() {
        testBook.setLanguage(null);
        Assertions.assertThat(testBook.getLanguage()).isNull();
    }

    @Test
    public void givenBook_getNumberOfPages_returnNotNull() {
        testBook.setNumberOfPages(100);
        Assertions.assertThat(testBook.getNumberOfPages()).isNotNull();
    }

    @Test
    public void givenNothing_getNumberOfPages_returnNothing() {
        testBook.setNumberOfPages(null);
        Assertions.assertThat(testBook.getNumberOfPages()).isNull();
    }

    @Test
    public void givenBook_getReleaseDate_returnNotNull() {
        testBook.setReleaseDate(LocalDate.now());
        Assertions.assertThat(testBook.getReleaseDate()).isNotNull();
    }

    @Test
    public void givenNothing_getReleaseDate_returnNothing() {
        testBook.setReleaseDate(null);
        Assertions.assertThat(testBook.getReleaseDate()).isNull();
    }

    @Test
    public void givenBook_getExemplars_returnNotNull() {
        List<Exemplary> testExemplars = new ArrayList<>();
        testBook.setExemplars(testExemplars);
        Assertions.assertThat(testBook.getExemplars()).isNotNull();
    }

    @Test
    public void givenNothing_getExemplars_returnNothing() {
        testBook.setExemplars(null);
        Assertions.assertThat(testBook.getExemplars()).isNull();
    }

    @Test
    public void givenBook_getLibraryID_returnNotNull() {
        Library testLibrary = new Library();
        testBook.setLibrary(testLibrary);
        Assertions.assertThat(testBook.getLibrary()).isNotNull();
    }

    @Test
    public void givenNothing_getLibraryID_returnNothing() {
        testBook.setLibrary(null);
        Assertions.assertThat(testBook.getLibrary()).isNull();
    }
}
