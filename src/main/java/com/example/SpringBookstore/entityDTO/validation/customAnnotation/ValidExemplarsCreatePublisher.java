package com.example.SpringBookstore.entityDTO.validation.customAnnotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.SpringBookstore.entityDTO.validation.validator.ExemplarsCreatePublisherValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExemplarsCreatePublisherValidator.class)
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidExemplarsCreatePublisher {
    String message() default "The publisher must be specified.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
