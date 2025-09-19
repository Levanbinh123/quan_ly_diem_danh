package com.example.quanLy_diem_danh.Entity.dto;

import com.example.quanLy_diem_danh.Entity.emum_.Roles;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String phone;
    private String fullName;
    private Roles roles;
    private String  avartarUrl;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
