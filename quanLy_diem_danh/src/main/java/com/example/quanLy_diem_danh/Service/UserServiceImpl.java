package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Entity.ClassStudent;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.UserDto;
import com.example.quanLy_diem_danh.Entity.mapper.UserMapper;
import com.example.quanLy_diem_danh.Repository.UserRepository;
import com.example.quanLy_diem_danh.Service.service.UserService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.configuration.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User updateUser(Long id, UserDto userDto) throws UserExcepion {
        User user = userRepository.findById(id).get();

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        User savedUSer=userRepository.save(user);
        return savedUSer;
    }

    @Override
    public List<UserDto> getAllUser() throws UserExcepion {
        List< User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
            for(User user : users){
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setPhone(user.getPhone());
                userDto.setEmail(user.getEmail());
                userDto.setPassword(user.getPassword());
                userDtos.add(userDto);

            }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) throws UserExcepion {
        User user=userRepository.findById(id).get();
        return UserMapper.toDto(user);
    }

    @Override
    public User createUser(User user) throws UserExcepion {
        User userSaved=userRepository.save(user);

        return userSaved;
    }

    @Override
    public void changePassword(Long id, String password) throws UserExcepion {

    }

    @Override
    public User getUserByToken(String token) throws UserExcepion {
        String email= JwtProvider.getEmailFromToken(token);
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public void deleteUser(Long id) throws UserExcepion {
        User user=userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Override
    public void userInviteClass(Long id, String token) throws UserExcepion {
        String mail=JwtProvider.getEmailFromToken(token);
        User user=userRepository.findByEmail(mail);
        ClassStudent classStudent=new ClassStudent();
        classStudent.setStudent(user);

    }


}
