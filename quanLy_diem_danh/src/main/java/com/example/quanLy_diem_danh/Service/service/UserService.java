package com.example.quanLy_diem_danh.Service.service;

import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.UserDto;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;

import java.util.List;

public interface UserService
{
    public User updateUser(Long id,UserDto userDto) throws UserExcepion;
    public List<UserDto> getAllUser() throws UserExcepion;
    public UserDto getUserById(Long id) throws UserExcepion;
    public User createUser(User user) throws UserExcepion;
    public  void changePassword(Long id, String password) throws UserExcepion;
    public User getUserByToken(String token) throws UserExcepion;
    public void deleteUser(Long id) throws UserExcepion;
    public void userInviteClass(Long id, String token) throws UserExcepion;
}
