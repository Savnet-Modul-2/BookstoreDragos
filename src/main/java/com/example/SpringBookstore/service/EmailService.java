package com.example.SpringBookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private final Integer verificationTime = 5;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public Integer getVerificationTime() {
        return verificationTime;
    }

    @Async
    public void sendEmail(String recipient, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setFrom(sender);
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(text);

        javaMailSender.send(email);
    }

    public String generateVerificationCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100000, 999999));
    }
}
