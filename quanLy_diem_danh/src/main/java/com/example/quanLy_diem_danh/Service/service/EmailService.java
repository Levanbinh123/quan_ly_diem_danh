package com.example.quanLy_diem_danh.Service.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmailWithToken(String userEmail, String link) throws MessagingException;
}
