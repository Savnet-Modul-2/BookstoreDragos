package com.example.SpringBookstore.entityDTO.validation.customAnnotation;

import com.example.SpringBookstore.entityDTO.validation.validator.LibrarianHasLibraryValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {LibrarianHasLibraryValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidHasLibrary {
    String message() default "The library must be provided.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
