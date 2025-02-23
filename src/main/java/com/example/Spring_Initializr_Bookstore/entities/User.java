package com.example.Spring_Initializr_Bookstore.entities;

import com.example.Spring_Initializr_Bookstore.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "users")
@Table(name = "users", schema = "public")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "YEAR_OF_BIRTH")
    private LocalDate yearOfBirth;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "VERIFIED_ACCOUNT")
    private Boolean verifiedAccount = false;

    @Column(name = "VERIFICATION_CODE")
    private String verificationCode;

    @Column(name = "VERIFICATION_CODE_GENERATION_TIME")
    private LocalDateTime verificationCodeGenerationTime;

    @Column(name = "LOGGED_IN")
    private Boolean loggedIn = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth.getYear();
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = LocalDate.ofYearDay(yearOfBirth, 1);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getVerifiedAccount() {
        return verifiedAccount;
    }

    public void setVerifiedAccount(Boolean verifiedAccount) {
        this.verifiedAccount = verifiedAccount;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public LocalDateTime getVerificationCodeGenerationTime() {
        return verificationCodeGenerationTime;
    }

    public void setVerificationCodeGenerationTime(LocalDateTime verificationCodeGenerationTime) {
        this.verificationCodeGenerationTime = verificationCodeGenerationTime;
    }
}
