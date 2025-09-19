package com.example.quanLy_diem_danh.Service.service;

import com.example.quanLy_diem_danh.Entity.dto.UserDto;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse register(UserDto userDto) throws Exception, UserExcepion;
    AuthResponse login(UserDto userDto) throws UserExcepion;
}
