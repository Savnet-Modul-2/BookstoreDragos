package com.example.SpringBookstore.entityDTO.validation.validator;

import com.example.SpringBookstore.entityDTO.BookDTO;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidBookReleaseDateNotInTheFuture;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BookReleaseDateNotInTheFutureValidator implements ConstraintValidator<ValidBookReleaseDateNotInTheFuture, BookDTO> {
    @Override
    public void initialize(ValidBookReleaseDateNotInTheFuture validBookReleaseDateNotInTheFuture) {
    }

    @Override
    public boolean isValid(BookDTO bookDTO, ConstraintValidatorContext context) {
        return !bookDTO.getReleaseDate().isAfter(LocalDate.now());
    }
}
