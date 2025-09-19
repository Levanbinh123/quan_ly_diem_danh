package com.example.quanLy_diem_danh.payload.response;


import com.example.quanLy_diem_danh.Entity.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
    private UserDto userDto;
}
