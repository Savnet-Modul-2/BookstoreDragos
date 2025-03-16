package com.example.SpringBookstore.entityDTO.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({BasicInformation.class, AdvancedInformation.class})
public interface ValidationOrder {
}
