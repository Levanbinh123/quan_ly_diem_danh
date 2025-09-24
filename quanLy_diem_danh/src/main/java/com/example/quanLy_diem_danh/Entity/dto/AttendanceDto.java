package com.example.quanLy_diem_danh.Entity.dto;

import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.emum_.AttendanceStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AttendanceDto {
    private Long id;
    private String className;
    private SessionDto session;
    private UserDto student;
    private AttendanceStatus status;
    private LocalDateTime timeChecked;
    private String method;
    private LocalDateTime createdAt;

}
