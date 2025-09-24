package com.example.quanLy_diem_danh.Controlerr;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.AdminDto.Responce_ClassResponceEntityForAdminDto;
import com.example.quanLy_diem_danh.Entity.dto.ClassEntityDto;
import com.example.quanLy_diem_danh.Service.service.ClassEntityService;
import com.example.quanLy_diem_danh.Service.service.UserService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.configuration.JwtProvider;
import com.example.quanLy_diem_danh.payload.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassEntityController {
    @Autowired
    private ClassEntityService classEntityService;
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<ClassEmtity> createClassEntity(@RequestBody ClassEntityDto classEntityDto,@RequestHeader("Authorization")String token) throws UserExcepion {
        User findUse= userService.getUserByToken(token);
        return ResponseEntity.ok(classEntityService.createClassEntity(classEntityDto, findUse.getId()));
    }
    @GetMapping()
    public ResponseEntity<List<Responce_ClassResponceEntityForAdminDto>> getAllClasses(){
        return ResponseEntity.ok(classEntityService.getAllClasses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteClassById(@PathVariable Long id) throws Exception {
        classEntityService.deleteClassById(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("deleted success");
        return ResponseEntity.ok(apiResponse);
    }
    }
