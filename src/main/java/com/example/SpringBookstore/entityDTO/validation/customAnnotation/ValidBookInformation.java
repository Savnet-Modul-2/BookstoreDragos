package com.example.SpringBookstore.entityDTO.validation.customAnnotation;

import com.example.SpringBookstore.entityDTO.validation.validator.BookInformationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {BookInformationValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidBookInformation {
    String message() default "The title and author must be specified.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
