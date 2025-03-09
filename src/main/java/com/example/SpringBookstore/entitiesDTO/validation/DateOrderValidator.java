package com.example.SpringBookstore.entitiesDTO.validation;

import com.example.SpringBookstore.entitiesDTO.ReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateOrderValidator implements ConstraintValidator<ValidDateOrder, ReservationDTO> {
    @Override
    public void initialize(ValidDateOrder validDateOrder) {
    }

    @Override
    public boolean isValid(ReservationDTO reservationDTO, ConstraintValidatorContext context) {
        return reservationDTO.getEndDate().isAfter(reservationDTO.getStartDate());
    }
}
