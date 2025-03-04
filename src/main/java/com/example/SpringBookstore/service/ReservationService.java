package com.example.SpringBookstore.service;

import com.example.SpringBookstore.ReservationStatus;
import com.example.SpringBookstore.entities.*;
import com.example.SpringBookstore.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.InputMismatchException;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final LibrarianRepository librarianRepository;
    private final BookRepository bookRepository;
    private final ExemplaryRepository exemplaryRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, LibrarianRepository librarianRepository, BookRepository bookRepository, ExemplaryRepository exemplaryRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.librarianRepository = librarianRepository;
        this.bookRepository = bookRepository;
        this.exemplaryRepository = exemplaryRepository;
    }

    public Page<Book> searchBooks(String title, String author, Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageSize != null) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            return bookRepository.searchBooks(title, author, pageable);
        }

        return bookRepository.searchBooks(title, author, Pageable.unpaged());
    }

    public Reservation reserveBook(Long userID, Long bookID, LocalDate startDate, LocalDate endDate) {
        Exemplary exemplary = exemplaryRepository.reserveExemplary(bookID, startDate, endDate)
                .orElseThrow(() -> new EntityNotFoundException("No exemplars of book with ID " + bookID + " available."));

        if (startDate.isAfter(endDate) || startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            throw new InputMismatchException("Reservation unsuccessful. Invalid reservation dates.");
        }

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + " not found."));

        bookRepository.findById(bookID)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookID + " not found."));

        Reservation reservation = new Reservation();

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(ReservationStatus.PENDING);

        exemplary.addReservation(reservation);
        user.addReservation(reservation);

        reservationRepository.save(reservation);
        exemplaryRepository.save(exemplary);
        userRepository.save(user);

        return reservation;
    }

    public Reservation updateStatus(Long librarianID, Long reservationID) {
        librarianRepository.findById(librarianID)
                .orElseThrow(() -> new EntityNotFoundException("Librarian with ID " + librarianID + " not found."));

        Reservation reservation = reservationRepository.findById(reservationID)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + reservationID + " not found."));

        if (reservation.getExemplary().getBook().getLibrary().getLibrarian().getID().equals(librarianID)) {
            if (reservation.getStatus().isNextStatePossible(ReservationStatus.IN_PROGRESS)) {
                reservation.setStatus(ReservationStatus.IN_PROGRESS);
            } else if (reservation.getStatus().isNextStatePossible(ReservationStatus.FINISHED)) {
                reservation.setStatus(ReservationStatus.FINISHED);
            }
        }

        return reservationRepository.save(reservation);
    }
}
