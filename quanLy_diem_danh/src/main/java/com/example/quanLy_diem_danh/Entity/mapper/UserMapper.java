package com.example.quanLy_diem_danh.Entity.mapper;

import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDto toDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setEmail(user.getEmail());
    userDto.setPassword(user.getPassword());
    userDto.setFullName(user.getFullName());
    userDto.setRoles(user.getRoles());
    userDto.setPhone(user.getPhone());
    userDto.setAvartarUrl(user.getAvartarUrl());
    userDto.setActive(user.isActive());
    userDto.setCreatedAt(user.getCreatedAt());
    userDto.setUpdatedAt(user.getUpdatedAt());
    return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());
        user.setRoles(userDto.getRoles());
        user.setPhone(userDto.getPhone());
        user.setAvartarUrl(userDto.getAvartarUrl());
        user.setActive(userDto.isActive());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        return user;
    }
}