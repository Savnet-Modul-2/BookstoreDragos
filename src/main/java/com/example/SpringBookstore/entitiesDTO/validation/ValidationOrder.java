package com.example.SpringBookstore.entitiesDTO.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({BasicInformation.class, AdvancedInformation.class})
public interface ValidationOrder {
}
