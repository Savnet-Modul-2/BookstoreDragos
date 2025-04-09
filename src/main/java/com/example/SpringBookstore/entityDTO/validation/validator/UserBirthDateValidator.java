package com.example.SpringBookstore.entityDTO.validation.validator;

import com.example.SpringBookstore.entityDTO.UserDTO;
import com.example.SpringBookstore.entityDTO.validation.customAnnotation.ValidUserBirthDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class UserBirthDateValidator implements ConstraintValidator<ValidUserBirthDate, UserDTO> {
    @Override
    public void initialize(ValidUserBirthDate validUserBirthDate) {
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        if (userDTO.getBirthDate().isAfter(LocalDate.now())) return false;

        int age = LocalDate.now().getYear() - userDTO.getBirthDate().getYear();

        if (LocalDate.now().getMonth().getValue() < userDTO.getBirthDate().getMonth().getValue() ||
                (LocalDate.now().getMonth().getValue() == userDTO.getBirthDate().getMonth().getValue() &&
                        LocalDate.now().getDayOfMonth() < userDTO.getBirthDate().getDayOfMonth())) {
            age--;
        }

        return age >= 18;
    }
}
