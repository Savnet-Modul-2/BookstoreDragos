package com.example.SpringBookstore.service;

import com.example.SpringBookstore.ReservationStatus;
import com.example.SpringBookstore.entity.*;
import com.example.SpringBookstore.entityDTO.ReservationFiltersDTO;
import com.example.SpringBookstore.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final LibrarianRepository librarianRepository;
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final ExemplaryRepository exemplaryRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, LibrarianRepository librarianRepository, LibraryRepository libraryRepository, BookRepository bookRepository, ExemplaryRepository exemplaryRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.librarianRepository = librarianRepository;
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.exemplaryRepository = exemplaryRepository;
    }

    public Page<Book> searchBooks(String title, String author, Integer pageNumber, Integer pageSize) {
        Pageable pageable = (pageNumber != null && pageSize != null) ? PageRequest.of(pageNumber, pageSize) : Pageable.unpaged();
        return bookRepository.searchBooks(title, author, pageable);
    }

    public Page<Reservation> findReservationsFromLibraryForPeriod(Long libraryID, ReservationFiltersDTO reservationFiltersDTO, String sortDirection, String sortCriteria, Integer pageNumber, Integer pageSize) {
        libraryRepository.findById(libraryID)
                .orElseThrow(() -> new EntityNotFoundException("Library with ID" + libraryID + " not found."));

        List<String> reservationStatusFilterStrings = reservationFiltersDTO.reservationStatusFilters().stream()
                .map(Enum::name)
                .toList();

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortCriteria);
        Pageable pageable = (pageNumber != null && pageSize != null) ? PageRequest.of(pageNumber, pageSize, sort) : Pageable.unpaged();

        return libraryRepository.findReservationByLibraryIdForPeriod(libraryID, reservationFiltersDTO.startDate(), reservationFiltersDTO.endDate(), reservationStatusFilterStrings, pageable);
    }

    public Page<Reservation> findReservationsFromUserByStatus(Long userID, ReservationFiltersDTO reservationFiltersDTO, String sortDirection, String sortCriteria, Integer pageNumber, Integer pageSize) {
        userRepository.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + " not found."));

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortCriteria);
        Pageable pageable = (pageNumber != null && pageSize != null) ? PageRequest.of(pageNumber, pageSize, sort) : Pageable.unpaged();

        if (reservationFiltersDTO.reservationStatusFilters() != null && !reservationFiltersDTO.reservationStatusFilters().isEmpty()) {
            List<String> reservationStatusFilterStrings = reservationFiltersDTO.reservationStatusFilters().stream()
                    .map(Enum::name)
                    .toList();

            return userRepository.findReservationFromUserByStatus(userID, reservationStatusFilterStrings, pageable);
        }

        return userRepository.findReservationFromUserByStatus(userID, null, pageable);
    }

    @Transactional
    public Reservation reserveBook(Long userID, Long bookID, LocalDate startDate, LocalDate endDate) {
        Exemplary exemplary = exemplaryRepository.reserveExemplary(bookID, startDate, endDate)
                .orElseThrow(() -> new EntityNotFoundException("No exemplars of book with ID " + bookID + " available."));

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + " not found."));

        Reservation reservation = new Reservation();

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(ReservationStatus.PENDING);

        exemplary.addReservation(reservation);
        exemplary.setUpdateTime(LocalDateTime.now());

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
