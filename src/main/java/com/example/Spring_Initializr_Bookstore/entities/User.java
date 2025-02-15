package com.example.Spring_Initializr_Bookstore.entities;

import com.example.Spring_Initializr_Bookstore.Gender;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "users")
@Table(name = "users", schema = "public")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "YEAROFBIRTH")
    private Integer yearOfBirth;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "VERIFIED ACCOUNT")
    private Boolean verifiedAccount = false;

    @Column(name = "VERIFICATION CODE")
    private String verificationCode;

    @Column(name = "VERIFICATION CODE GENERATION TIME")
    private LocalDateTime verificationCodeGenerationTime;

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
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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
