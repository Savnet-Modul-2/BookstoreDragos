package com.example.SpringBookstore.controller;

import com.example.SpringBookstore.entities.Book;
import com.example.SpringBookstore.entities.Reservation;
import com.example.SpringBookstore.entitiesDTO.ReservationDTO;
import com.example.SpringBookstore.mapper.BookMapper;
import com.example.SpringBookstore.mapper.ReservationMapper;
import com.example.SpringBookstore.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<?> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Page<Book> foundBooks = reservationService.searchBooks(title, author, pageNumber, pageSize);

        return ResponseEntity.ok(foundBooks.stream()
                .map(BookMapper::book2BookDTO)
                .toList());
    }

    @PostMapping(path = "/{userID}/{bookID}")
    public ResponseEntity<?> reserveBook(@PathVariable(name = "userID") Long userID, @PathVariable(name = "bookID") Long bookID, @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.reserveBook(userID, bookID, reservationDTO.getStartDate(), reservationDTO.getEndDate());
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(reservation));
    }

    @PutMapping(path = "/{librarianID}/{reservationID}")
    public ResponseEntity<?> updateStatus(@PathVariable(name = "librarianID") Long librarianID, @PathVariable(name = "reservationID") Long reservationID) {
        Reservation updatedReservation = reservationService.updateStatus(librarianID, reservationID);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(updatedReservation));
    }
}
