package com.example.SpringBookstore.repositories;

import com.example.SpringBookstore.entities.Library;
import com.example.SpringBookstore.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Query(value = """
            SELECT r.* FROM reservations r
            JOIN exemplars e ON r.EXEMPLARY_ID = e.ID
            JOIN books b ON e.BOOK_ID = b.ID
            JOIN libraries l ON b.LIBRARY_ID = l.ID
            WHERE l.ID = :libraryID
            AND r.START_DATE >= :startDate
            AND r.END_DATE <= :endDate
            """,
            countQuery = """
                    SELECT COUNT(*) FROM reservations r
                    JOIN exemplars e ON r.EXEMPLARY_ID = e.ID
                    JOIN books b ON e.BOOK_ID = b.ID
                    JOIN libraries l ON b.LIBRARY_ID = l.ID
                    WHERE l.ID = :libraryID
                    AND r.START_DATE >= :startDate
                    AND r.END_DATE <= :endDate
                    """,
            nativeQuery = true)
    Page<Reservation> findReservationByLibraryIdForPeriod(@Param(value = "libraryID") Long libraryID, @Param(value = "startDate") LocalDate startDate, @Param(value = "endDate") LocalDate endDate, Pageable pageable);
}
