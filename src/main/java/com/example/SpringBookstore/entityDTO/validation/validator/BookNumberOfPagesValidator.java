package com.example.SpringBookstore.entityDTO.validation.validator;

import com.example.SpringBookstore.entityDTO.BookDTO;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidBookNumberOfPages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookNumberOfPagesValidator implements ConstraintValidator<ValidBookNumberOfPages, BookDTO> {
    @Override
    public void initialize(ValidBookNumberOfPages validBookNumberOfPages) {
    }

    @Override
    public boolean isValid(BookDTO bookDTO, ConstraintValidatorContext context) {
        return bookDTO.getNumberOfPages() >= 0;
    }
}
