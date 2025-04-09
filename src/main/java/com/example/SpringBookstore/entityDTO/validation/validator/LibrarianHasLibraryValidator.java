package com.example.SpringBookstore.entityDTO.validation.validator;

import com.example.SpringBookstore.entityDTO.LibrarianDTO;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidHasLibrary;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LibrarianHasLibraryValidator implements ConstraintValidator<ValidHasLibrary, LibrarianDTO> {
    @Override
    public void initialize(ValidHasLibrary validHasLibrary) {
    }

    @Override
    public boolean isValid(LibrarianDTO librarianDTO, ConstraintValidatorContext context) {
        return librarianDTO.getLibraryDTO() != null;
    }
}
