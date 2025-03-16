package com.example.SpringBookstore.entityDTO.validation;

import com.example.SpringBookstore.entityDTO.ExemplarsCreateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExemplarsCreateNumberValidator implements ConstraintValidator<ValidExemplarsCreateNumber, ExemplarsCreateDTO> {
    @Override
    public void initialize(ValidExemplarsCreateNumber validExemplarsCreateNumber) {
    }

    @Override
    public boolean isValid(ExemplarsCreateDTO exemplarsCreateDTO, ConstraintValidatorContext context) {
        return exemplarsCreateDTO.getNumberOfExemplars() > 0;
    }
}
