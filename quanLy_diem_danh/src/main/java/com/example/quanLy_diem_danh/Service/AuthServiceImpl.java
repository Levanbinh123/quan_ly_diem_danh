package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.UserDto;
import com.example.quanLy_diem_danh.Entity.emum_.Roles;
import com.example.quanLy_diem_danh.Entity.mapper.UserMapper;
import com.example.quanLy_diem_danh.Repository.UserRepository;
import com.example.quanLy_diem_danh.Service.service.AuthService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.configuration.JwtProvider;
import com.example.quanLy_diem_danh.payload.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public AuthResponse register(UserDto userDto) throws Exception, UserExcepion {
        User user=userRepository.findByEmail(userDto.getEmail());
        if(user!=null){
            throw  new UserExcepion("Email Already Exists");
        }
        if(userDto.getRoles().equals(Roles.Admin)){
            throw  new UserExcepion("Admin Role is not allowed");
        }
        try {
            User newUser=new User();
            newUser.setEmail(userDto.getEmail());
            newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            newUser.setRoles(userDto.getRoles() != null ? userDto.getRoles() : Roles.Student);
            newUser.setFullName(userDto.getFullName());
            newUser.setPhone(userDto.getPhone());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            User savedUser=userRepository.save(newUser);
            Authentication authentication = authenticate(userDto.getEmail(), userDto.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt= JwtProvider.generateToken(authentication);
            AuthResponse authResponse=new AuthResponse();
            authResponse.setToken(jwt);
            authResponse.setMessage("Success");
            authResponse.setUserDto(UserMapper.toDto(savedUser));
            return authResponse;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserExcepion {
        String email=userDto.getEmail();
        String password=userDto.getPassword();
        Authentication  authentication=authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=JwtProvider.generateToken(authentication);
        User user=userRepository.findByEmail(email);
        userRepository.save(user);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setMessage("login Success");
        authResponse.setUserDto(UserMapper.toDto(user));
        return authResponse;
    }
    private Authentication authenticate(String email, String password) throws UserExcepion {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new UserExcepion("Email is do no Exists");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserExcepion("Wrong password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
