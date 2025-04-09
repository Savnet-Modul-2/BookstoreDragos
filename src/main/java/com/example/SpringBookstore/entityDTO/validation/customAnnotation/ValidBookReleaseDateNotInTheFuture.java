package com.example.SpringBookstore.entityDTO.validation.customAnnotation;

import com.example.SpringBookstore.entityDTO.validation.validator.BookReleaseDateNotInTheFutureValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {BookReleaseDateNotInTheFutureValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidBookReleaseDateNotInTheFuture {
    String message() default "The release date must be valid.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
