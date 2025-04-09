package com.example.SpringBookstore.repository;

import com.example.SpringBookstore.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = """
                SELECT reservation FROM reservation reservation
                WHERE reservation.endDate < :today
                AND reservation.reservationStatus = 'IN_PROGRESS'
            """)
    List<Reservation> findAllReservationsToBeDelayed(LocalDate today);

    @Query(value = """
                SELECT reservation FROM reservation reservation
                WHERE reservation.startDate < :today
                AND reservation.reservationStatus = 'PENDING'
            """)
    List<Reservation> findAllReservationsToBeCanceled(LocalDate today);
}
