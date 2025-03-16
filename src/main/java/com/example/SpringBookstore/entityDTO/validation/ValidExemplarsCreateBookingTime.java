package com.example.SpringBookstore.entityDTO.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExemplarsCreateBookingTimeValidator.class)
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidExemplarsCreateBookingTime {
    String message() default "The maximum booking time must be positive.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
