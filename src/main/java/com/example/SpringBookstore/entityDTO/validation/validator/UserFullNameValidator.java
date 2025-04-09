package com.example.SpringBookstore.entityDTO.validation.validator;

import com.example.SpringBookstore.entityDTO.UserDTO;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidUserFullName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserFullNameValidator implements ConstraintValidator<ValidUserFullName, UserDTO> {
    @Override
    public void initialize(ValidUserFullName validUserFullName) {
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        return userDTO.getFirstName() != null && userDTO.getLastName() != null && !userDTO.getFirstName().isEmpty() && !userDTO.getLastName().isEmpty();
    }
}
