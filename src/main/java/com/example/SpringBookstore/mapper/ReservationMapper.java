package com.example.SpringBookstore.mapper;

import com.example.SpringBookstore.dto.ReservationDTO;
import com.example.SpringBookstore.entity.Reservation;

public class ReservationMapper {
    public static Reservation reservationDTO2Reservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStatus(reservationDTO.getStatus());

        return reservation;
    }

    public static ReservationDTO reservation2ReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setStatus(reservation.getStatus());

        return reservationDTO;
    }
}
