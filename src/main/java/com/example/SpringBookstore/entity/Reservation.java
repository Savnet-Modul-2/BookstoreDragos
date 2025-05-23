package com.example.SpringBookstore.entity;

import com.example.SpringBookstore.ReservationStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "reservation")
@Table(name = "reservations", schema = "public")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXEMPLARY_ID")
    private Exemplary exemplary;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ReservationStatus getStatus() {
        return reservationStatus;
    }

    public void setStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Exemplary getExemplary() {
        return exemplary;
    }

    public void setExemplary(Exemplary exemplary) {
        this.exemplary = exemplary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
