package com.example.SpringBookstore.entitiesDTO.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateInTheFutureValidator.class)
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidDateInTheFuture {
    String message() default "The start date and end date must be set in the future.";
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};
}
