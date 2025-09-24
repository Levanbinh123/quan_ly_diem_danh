package com.example.quanLy_diem_danh.Controlerr;

import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.UserDto;
import com.example.quanLy_diem_danh.Service.service.UserService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.payload.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/profile")
    public ResponseEntity<User> getUerByToken(@RequestHeader("Authorization") String token) throws UserExcepion {
        return ResponseEntity.ok(userService.getUserByToken(token));
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user, @RequestParam Long id) throws UserExcepion {
        return ResponseEntity.ok(userService.updateUser(id,user));
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() throws UserExcepion {
        List<UserDto> UserDtos=userService.getAllUser();
        return ResponseEntity.ok(UserDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@RequestParam Long id) throws UserExcepion {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@RequestParam Long id) throws UserExcepion {
        userService.deleteUser(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("delete user success");
        return ResponseEntity.ok(apiResponse);
    }

}
