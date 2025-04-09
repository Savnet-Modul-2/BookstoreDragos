package com.example.SpringBookstore.unitTest.repository;

import com.example.SpringBookstore.entity.Book;
import com.example.SpringBookstore.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class BookRepositoryTests {
    private final BookRepository testBookRepository;

    private Book testBook;

    @Autowired
    public BookRepositoryTests(BookRepository testBookRepository) {
        this.testBookRepository = testBookRepository;
    }

    @BeforeEach
    public void setup() {
        testBook = new Book();

        testBook.setTitle("testTitle");
        testBook.setAuthor("testAuthor");
    }

    @AfterEach
    public void tearDown() {
        testBookRepository.deleteAll();
    }

    @Test
    public void givenTitleAndAuthor_searchBooks_returnNotEmpty() {
        String testTitle = testBook.getTitle();
        String testAuthor = testBook.getAuthor();

        testBookRepository.save(testBook);
        Page<Book> foundBooks = testBookRepository.searchBooks(testTitle, testAuthor, Pageable.unpaged());

        Assertions.assertThat(foundBooks).isNotEmpty();
    }

    @Test
    public void givenTitle_searchBooks_returnNotEmpty() {
        String testTitle = testBook.getTitle();

        testBookRepository.save(testBook);
        Page<Book> foundBooks = testBookRepository.searchBooks(testTitle, null, Pageable.unpaged());

        Assertions.assertThat(foundBooks).isNotEmpty();
    }

    @Test
    public void givenAuthor_searchBooks_returnNotEmpty() {
        String testAuthor = testBook.getAuthor();

        testBookRepository.save(testBook);
        Page<Book> foundBooks = testBookRepository.searchBooks(null, testAuthor, Pageable.unpaged());

        Assertions.assertThat(foundBooks).isNotEmpty();
    }

    @Test
    public void givenNothing_searchBooks_returnEmpty() {
        testBookRepository.save(testBook);
        Page<Book> foundBooks = testBookRepository.searchBooks(null, null, Pageable.unpaged());

        Assertions.assertThat(foundBooks).isEmpty();
    }
}
