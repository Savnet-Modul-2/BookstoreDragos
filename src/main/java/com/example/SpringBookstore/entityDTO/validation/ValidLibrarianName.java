package com.example.SpringBookstore.entityDTO.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {LibrarianNameValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidLibrarianName {
    String message() default "The name must be specified.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
