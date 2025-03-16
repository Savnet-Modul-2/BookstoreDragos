package com.example.SpringBookstore.mapper;

import com.example.SpringBookstore.entity.Reservation;
import com.example.SpringBookstore.entityDTO.ReservationDTO;

public class ReservationMapper {
    public static ReservationDTO reservation2ReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setID(reservation.getID());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setExemplaryDTO(ExemplaryMapper.exemplary2ExemplaryDTO(reservation.getExemplary()));
        reservationDTO.setUserDTO(UserMapper.user2UserDTO(reservation.getUser()));

        return reservationDTO;
    }
}
