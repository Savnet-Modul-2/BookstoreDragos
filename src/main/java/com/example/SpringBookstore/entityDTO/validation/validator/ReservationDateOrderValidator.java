package com.example.SpringBookstore.entityDTO.validation.validator;

import com.example.SpringBookstore.entityDTO.ReservationDTO;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidReservationDateOrder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReservationDateOrderValidator implements ConstraintValidator<ValidReservationDateOrder, ReservationDTO> {
    @Override
    public void initialize(ValidReservationDateOrder validReservationDateOrder) {
    }

    @Override
    public boolean isValid(ReservationDTO reservationDTO, ConstraintValidatorContext context) {
        return reservationDTO.getEndDate().isAfter(reservationDTO.getStartDate());
    }
}
