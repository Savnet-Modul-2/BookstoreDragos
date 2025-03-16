package com.example.SpringBookstore.entityDTO.validation;

import com.example.SpringBookstore.entityDTO.LibrarianDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LibrarianNameValidator implements ConstraintValidator<ValidLibrarianName, LibrarianDTO> {
    @Override
    public void initialize(ValidLibrarianName validLibrarianName) {
    }

    @Override
    public boolean isValid(LibrarianDTO librarianDTO, ConstraintValidatorContext context) {
        return librarianDTO.getName() != null && !librarianDTO.getName().isEmpty();
    }
}
