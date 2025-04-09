package com.example.SpringBookstore.entityDTO.validation.customAnnotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.SpringBookstore.entityDTO.validation.validator.UserFullNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = {UserFullNameValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidUserFullName {
    String message() default "The first name and last name must be specified.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
