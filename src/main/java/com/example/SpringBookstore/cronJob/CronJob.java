package com.example.SpringBookstore.cronJob;

import com.example.SpringBookstore.ReservationStatus;
import com.example.SpringBookstore.entity.Reservation;
import com.example.SpringBookstore.repository.ReservationRepository;
import com.example.SpringBookstore.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class CronJob {
    private final ReservationRepository reservationRepository;
    private final LibrarianService librarianService;

    @Autowired
    public CronJob(ReservationRepository reservationRepository, LibrarianService librarianService) {
        this.reservationRepository = reservationRepository;
        this.librarianService = librarianService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateReservationStatus() {
        List<Reservation> reservationsToBeDelayed = reservationRepository
                .findAllReservationsToBeDelayed(LocalDate.now());
        List<Reservation> reservationsToBeCanceled = reservationRepository
                .findAllReservationsToBeCanceled(LocalDate.now());

        if (!reservationsToBeDelayed.isEmpty()) {
            reservationsToBeDelayed.forEach(reservation -> reservation.setStatus(ReservationStatus.DELAYED));
            reservationRepository.saveAll(reservationsToBeDelayed);

            librarianService.delayedReservationsNotification(reservationsToBeDelayed);
        }

        if (!reservationsToBeCanceled.isEmpty()) {
            reservationsToBeCanceled.forEach(reservation -> reservation.setStatus(ReservationStatus.CANCELLED));
            reservationRepository.saveAll(reservationsToBeCanceled);
        }
    }
}
