package com.example.SpringBookstore.entityDTO.validation;

import com.example.SpringBookstore.entityDTO.ExemplarsCreateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExemplarsCreatePublisherValidator implements ConstraintValidator<ValidExemplarsCreatePublisher, ExemplarsCreateDTO> {
    @Override
    public void initialize(ValidExemplarsCreatePublisher validExemplarsCreatePublisher) {
    }

    @Override
    public boolean isValid(ExemplarsCreateDTO exemplarsCreateDTO, ConstraintValidatorContext context) {
        return exemplarsCreateDTO.getPublisher() != null && !exemplarsCreateDTO.getPublisher().isEmpty();
    }
}
