package com.example.SpringBookstore.entityDTO.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {LibraryInformationValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidLibraryInformation {
    String message() default "The name, address and phone number must be specified.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
