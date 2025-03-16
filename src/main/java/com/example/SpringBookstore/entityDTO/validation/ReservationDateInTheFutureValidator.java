package com.example.SpringBookstore.entityDTO.validation;

import com.example.SpringBookstore.entityDTO.ReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ReservationDateInTheFutureValidator implements ConstraintValidator<ValidReservationDateInTheFuture, ReservationDTO> {
    @Override
    public void initialize(ValidReservationDateInTheFuture validReservationDateInTheFuture) {
    }

    @Override
    public boolean isValid(ReservationDTO reservationDTO, ConstraintValidatorContext context) {
        return (reservationDTO.getStartDate().isAfter(LocalDate.now()) && reservationDTO.getEndDate().isAfter(LocalDate.now()));
    }
}
