package com.example.SpringBookstore.entityDTO.validation;

import com.example.SpringBookstore.entityDTO.LibraryDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LibraryInformationValidator implements ConstraintValidator<ValidLibraryInformation, LibraryDTO> {
    @Override
    public void initialize(ValidLibraryInformation validLibraryInformation) {
    }

    @Override
    public boolean isValid(LibraryDTO libraryDTO, ConstraintValidatorContext context) {
        return libraryDTO.getName() != null &&
                libraryDTO.getAddress() != null &&
                libraryDTO.getPhoneNumber() != null &&
                !libraryDTO.getName().isEmpty() &&
                !libraryDTO.getAddress().isEmpty() &&
                !libraryDTO.getPhoneNumber().isEmpty();
    }
}
