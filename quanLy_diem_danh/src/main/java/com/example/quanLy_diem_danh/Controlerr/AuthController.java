package com.example.quanLy_diem_danh.Controlerr;

import com.example.quanLy_diem_danh.Entity.dto.UserDto;
import com.example.quanLy_diem_danh.Service.service.AuthService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.payload.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto userDto) throws UserExcepion, Exception {
        return ResponseEntity.ok(authService.register(userDto));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) throws UserExcepion, Exception {
        return ResponseEntity.ok(authService.login(userDto));
    }
}
