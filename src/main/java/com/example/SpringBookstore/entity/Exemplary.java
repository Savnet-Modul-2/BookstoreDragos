package com.example.SpringBookstore.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "exemplary")
@Table(name = "exemplars", schema = "public")
public class Exemplary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "MAXIMUM_BOOKING_TIME")
    private Integer maximumBookingTime;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @OneToMany(mappedBy = "exemplary")
    private List<Reservation> reservations;

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getMaximumBookingTime() {
        return maximumBookingTime;
    }

    public void setMaximumBookingTime(Integer maximumBookingTime) {
        this.maximumBookingTime = maximumBookingTime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
            reservation.setExemplary(this);
        }
    }

    public void removeReservation(Reservation reservation) {
        if (reservations.contains(reservation)) {
            reservations.remove(reservation);
            reservation.setExemplary(null);
        }
    }
}
