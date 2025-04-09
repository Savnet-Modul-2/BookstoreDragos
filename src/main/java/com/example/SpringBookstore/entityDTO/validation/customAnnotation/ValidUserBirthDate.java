package com.example.SpringBookstore.entityDTO.validation.customAnnotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.SpringBookstore.entityDTO.validation.validator.UserBirthDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = {UserBirthDateValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidUserBirthDate {
    String message() default "The birthdate must be valid and indicate that the user is at least 18 years old.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
