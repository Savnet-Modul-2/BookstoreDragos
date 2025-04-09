package com.example.SpringBookstore.entityDTO.validation.customAnnotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.SpringBookstore.entityDTO.validation.validator.ReservationDateOrderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ReservationDateOrderValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidReservationDateOrder {
    String message() default "The start date must be before the end date.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
