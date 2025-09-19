package com.example.quanLy_diem_danh.Controlerr;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.dto.ClassEntityDto;
import com.example.quanLy_diem_danh.Service.service.ClassEntityService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class ClassEntityController {
    @Autowired
    private ClassEntityService classEntityService;
    @PostMapping()
    public ResponseEntity<ClassEmtity> createClassEntity(@RequestBody ClassEntityDto classEntityDto,@RequestHeader("Authorization")String token) throws UserExcepion {
        return ResponseEntity.ok(classEntityService.createClassEntity(classEntityDto, token));
    }
}
