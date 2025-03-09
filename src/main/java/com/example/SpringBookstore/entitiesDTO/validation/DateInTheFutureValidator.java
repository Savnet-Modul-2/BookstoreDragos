package com.example.SpringBookstore.entitiesDTO.validation;

import com.example.SpringBookstore.entitiesDTO.ReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateInTheFutureValidator implements ConstraintValidator<ValidDateInTheFuture, ReservationDTO> {
    @Override
    public void initialize(ValidDateInTheFuture validDateInTheFuture) {
    }

    @Override
    public boolean isValid(ReservationDTO reservationDTO, ConstraintValidatorContext context) {
        return (reservationDTO.getStartDate().isAfter(LocalDate.now()) && reservationDTO.getEndDate().isAfter(LocalDate.now()));
    }
}
