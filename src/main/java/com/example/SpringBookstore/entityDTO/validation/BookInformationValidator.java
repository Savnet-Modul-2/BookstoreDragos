package com.example.SpringBookstore.entityDTO.validation;

import com.example.SpringBookstore.entityDTO.BookDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookInformationValidator implements ConstraintValidator<ValidBookInformation, BookDTO> {
    @Override
    public void initialize(ValidBookInformation validBookInformation) {
    }

    @Override
    public boolean isValid(BookDTO bookDTO, ConstraintValidatorContext context) {
        return bookDTO.getTitle() != null &&
                bookDTO.getAuthor() != null &&
                !bookDTO.getTitle().isEmpty() &&
                !bookDTO.getAuthor().isEmpty();
    }
}
