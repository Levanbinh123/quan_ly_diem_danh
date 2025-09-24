package com.example.quanLy_diem_danh.Controlerr;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.ClassStudent;
import com.example.quanLy_diem_danh.Service.service.ClassStudentService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.payload.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-student")
public class ClassStudentController {
    @Autowired
    private ClassStudentService classStudentService;

    @PostMapping("/register/{classId}")
    public ResponseEntity<ApiResponse> registerClass(
            @PathVariable Long classId,
            @RequestHeader("Authorization") String token
    ) throws Exception, UserExcepion {
        ClassStudent cs = classStudentService.registerClass(classId, token);
        ApiResponse api = new ApiResponse();
        api.setMessage("Đăng ký lớp thành công");
        return ResponseEntity.ok(api);
    }
    @GetMapping("/classes")
    public ResponseEntity<List<ClassEmtity>> getClasses(@RequestHeader("Authorization") String token) throws UserExcepion, Exception {
        List<ClassEmtity> classes= classStudentService.getClasses(token);
        return ResponseEntity.ok(classes);
    }
    

}
