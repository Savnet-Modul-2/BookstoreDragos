package com.example.SpringBookstore.entityDTO.validation.customAnnotation;

import com.example.SpringBookstore.entityDTO.validation.validator.BookNumberOfPagesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {BookNumberOfPagesValidator.class})
@Target(value = ElementType.TYPE)
@Retention(value = RUNTIME)
public @interface ValidBookNumberOfPages {
    String message() default "The number of pages must be positive.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
