package com.example.SpringBookstore.entityDTO.validation.validator;

import com.example.SpringBookstore.entityDTO.ExemplarsCreateDTO;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidExemplarsCreateBookingTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExemplarsCreateBookingTimeValidator implements ConstraintValidator<ValidExemplarsCreateBookingTime, ExemplarsCreateDTO> {
    @Override
    public void initialize(ValidExemplarsCreateBookingTime validExemplarsCreateBookingTime) {
    }

    @Override
    public boolean isValid(ExemplarsCreateDTO exemplarsCreateDTO, ConstraintValidatorContext context) {
        return exemplarsCreateDTO.getMaximumBookingTime() > 0;
    }
}
