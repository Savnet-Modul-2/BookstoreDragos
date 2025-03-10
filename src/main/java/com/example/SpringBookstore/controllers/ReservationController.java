package com.example.SpringBookstore.controllers;

import com.example.SpringBookstore.ReservationStatus;
import com.example.SpringBookstore.entities.Book;
import com.example.SpringBookstore.entities.Reservation;
import com.example.SpringBookstore.entitiesDTO.ReservationDTO;
import com.example.SpringBookstore.entitiesDTO.validation.ValidationOrder;
import com.example.SpringBookstore.mappers.BookMapper;
import com.example.SpringBookstore.mappers.ReservationMapper;
import com.example.SpringBookstore.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(path = "/{userID}/{bookID}")
    public ResponseEntity<?> reserveBook(@PathVariable(name = "userID") Long userID, @PathVariable(name = "bookID") Long bookID, @Validated(ValidationOrder.class) @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.reserveBook(userID, bookID, reservationDTO.getStartDate(), reservationDTO.getEndDate());
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(reservation));
    }

    @GetMapping
    public ResponseEntity<?> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        Page<Book> foundBooks = reservationService.searchBooks(title, author, pageNumber, pageSize);

        return ResponseEntity.ok(foundBooks.stream()
                .map(BookMapper::book2BookDTO)
                .toList());
    }

    @GetMapping(path = "/library/{libraryID}")
    public ResponseEntity<?> findReservationsFromLibraryForPeriod(@PathVariable(value = "libraryID") Long libraryID, @RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate, @RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false, defaultValue = "ASC") String sortDirection, @RequestParam(required = false, defaultValue = "START_DATE") String sortCriteria) {
        Page<Reservation> foundReservations = reservationService.findReservationsFromLibraryForPeriod(libraryID, startDate, endDate, pageNumber, pageSize, sortDirection, sortCriteria);

        return ResponseEntity.ok(foundReservations.stream()
                .map(ReservationMapper::reservation2ReservationDTO)
                .toList());
    }

    @GetMapping(path = "/user/{userID}")
    public ResponseEntity<?> findReservationsFromUserByStatus(@PathVariable(value = "userID") Long userID, @RequestParam(required = false) ReservationStatus reservationStatus, @RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false, defaultValue = "ASC") String sortDirection, @RequestParam(required = false, defaultValue = "START_DATE") String sortCriteria) {
        Page<Reservation> foundReservations = reservationService.findReservationsFromUserByStatus(userID, reservationStatus, pageNumber, pageSize, sortDirection, sortCriteria);

        return ResponseEntity.ok(foundReservations.stream()
                .map(ReservationMapper::reservation2ReservationDTO)
                .toList());
    }

    @PutMapping(path = "/{librarianID}/{reservationID}")
    public ResponseEntity<?> updateStatus(@PathVariable(name = "librarianID") Long librarianID, @PathVariable(name = "reservationID") Long reservationID) {
        Reservation updatedReservation = reservationService.updateStatus(librarianID, reservationID);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(updatedReservation));
    }
}
