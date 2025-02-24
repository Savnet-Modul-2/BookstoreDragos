package com.example.SpringBookstore;

public enum Gender {
    MALE('M'),
    FEMALE('F');

    final Character genderInitial;

    Gender(Character genderInitial) {
        this.genderInitial = genderInitial;
    }
}
