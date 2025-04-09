package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entity.Book;
import com.example.SpringBookstore.entity.Reservation;
import com.example.SpringBookstore.entityDTO.ReservationDTO;
import com.example.SpringBookstore.entityDTO.ReservationFiltersDTO;
import com.example.SpringBookstore.entityDTO.validation.information.ValidationOrder;
import com.example.SpringBookstore.mapper.BookMapper;
import com.example.SpringBookstore.mapper.ReservationMapper;
import com.example.SpringBookstore.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(path = "/{userID}/{bookID}")
    public ResponseEntity<?> reserveBook(@PathVariable(name = "userID") Long userID,
                                         @PathVariable(name = "bookID") Long bookID,
                                         @Validated(value = ValidationOrder.class) @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.reserveBook(userID, bookID, reservationDTO.getStartDate(), reservationDTO.getEndDate());
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(reservation));
    }

    @GetMapping
    public ResponseEntity<?> searchBooks(@RequestParam(required = false) String title,
                                         @RequestParam(required = false) String author,
                                         @RequestParam(required = false) Integer pageNumber,
                                         @RequestParam(required = false) Integer pageSize) {
        Page<Book> foundBooks = reservationService.searchBooks(title, author, pageNumber, pageSize);

        return ResponseEntity.ok(foundBooks.stream()
                .map(BookMapper::book2BookDTO)
                .toList());
    }

    @GetMapping(path = "/library/{libraryID}")
    public ResponseEntity<?> findReservationsFromLibraryForPeriod(@PathVariable(value = "libraryID") Long libraryID,
                                                                  @RequestBody ReservationFiltersDTO reservationFiltersDTO,
                                                                  @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
                                                                  @RequestParam(required = false, defaultValue = "START_DATE") String sortCriteria,
                                                                  @RequestParam(required = false) Integer pageNumber,
                                                                  @RequestParam(required = false) Integer pageSize) {
        Page<Reservation> foundReservations = reservationService.findReservationsFromLibraryForPeriod(libraryID, reservationFiltersDTO, sortDirection, sortCriteria, pageNumber, pageSize);

        return ResponseEntity.ok(foundReservations.stream()
                .map(ReservationMapper::reservation2ReservationDTO)
                .toList());
    }

    @GetMapping(path = "/user/{userID}")
    public ResponseEntity<?> findReservationsFromUserByStatus(@PathVariable(value = "userID") Long userID,
                                                              @RequestBody ReservationFiltersDTO reservationFiltersDTO,
                                                              @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
                                                              @RequestParam(required = false, defaultValue = "START_DATE") String sortCriteria,
                                                              @RequestParam(required = false) Integer pageNumber,
                                                              @RequestParam(required = false) Integer pageSize) {
        Page<Reservation> foundReservations = reservationService.findReservationsFromUserByStatus(userID, reservationFiltersDTO, sortDirection, sortCriteria, pageNumber, pageSize);

        return ResponseEntity.ok(foundReservations.stream()
                .map(ReservationMapper::reservation2ReservationDTO)
                .toList());
    }

    @PutMapping(path = "/{librarianID}/{reservationID}")
    public ResponseEntity<?> updateStatus(@PathVariable(name = "librarianID") Long librarianID,
                                          @PathVariable(name = "reservationID") Long reservationID) {
        Reservation updatedReservation = reservationService.updateStatus(librarianID, reservationID);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(updatedReservation));
    }
}
