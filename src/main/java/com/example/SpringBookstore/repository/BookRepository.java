package com.example.SpringBookstore.repository;

import com.example.SpringBookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = """
            SELECT b FROM book b
            WHERE
            (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND
            (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))""")
    Page<Book> searchBooks(@Param("title") String title, @Param("author") String author, Pageable pageable);
}
