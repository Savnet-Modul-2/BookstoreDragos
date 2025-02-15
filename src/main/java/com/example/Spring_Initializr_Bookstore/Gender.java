package com.example.Spring_Initializr_Bookstore;

public enum Gender {
    MALE('M'),
    FEMALE('F');

    final Character genderInitial;

    Gender(Character genderInitial) {
        this.genderInitial = genderInitial;
    }
}
