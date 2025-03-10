package com.example.SpringBookstore.repositories;

import com.example.SpringBookstore.entities.Reservation;
import com.example.SpringBookstore.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String emailAddress);

    @Query(value = """
            SELECT r.* FROM reservations r
            WHERE r.USER_ID = :userID
            AND (r.STATUS = NULL OR r.STATUS = :reservationStatusName)
            """,
            countQuery = """
                    SELECT COUNT(*) FROM reservations r
                    WHERE r.USER_ID = :userID
                    AND (r.STATUS = NULL OR r.STATUS = :reservationStatusName)
                    """,
            nativeQuery = true)
    Page<Reservation> findReservationFromUserByStatus(@Param(value = "userID") Long userID, @Param(value = "reservationStatusName") String reservationStatusName, Pageable pageable);
}
