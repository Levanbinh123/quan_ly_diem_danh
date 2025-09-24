package com.example.quanLy_diem_danh.Controlerr;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.ClassEntityDto;
import com.example.quanLy_diem_danh.Entity.dto.SessionDto;
import com.example.quanLy_diem_danh.Entity.mapper.SessionMapper;
import com.example.quanLy_diem_danh.Repository.ClassEntityRepository;
import com.example.quanLy_diem_danh.Repository.SessionRepository;
import com.example.quanLy_diem_danh.Service.service.ClassEntityService;
import com.example.quanLy_diem_danh.Service.service.SessionService;
import com.example.quanLy_diem_danh.Service.service.UserService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.payload.response.ApiResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    @Autowired
    private ClassEntityService classEntityService;
    @Autowired
    private ClassEntityRepository classRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;
    // Tạo nhiều buổi học cho một lớp
    @PostMapping("/class/{classId}")
    public ResponseEntity<List<SessionDto>> createSessions(
            @PathVariable Long classId,
            @RequestBody List<SessionDto> sessionDtos,
            @RequestHeader("Authorization") String token
    ) throws UserExcepion, Exception {
        User teacher = userService.getUserByToken(token);
        ClassEmtity findClass = classRepository.findById(classId)
                .orElseThrow(() -> new UserExcepion("Class not found"));
        // Kiểm tra lớp thuộc giáo viên
        if (!findClass.getTeacher().getId().equals(teacher.getId())) {
            throw new UserExcepion("You are not the teacher of this class");
        }

        List<Session> savedSessions = sessionService.createSession(sessionDtos,classId);


        return ResponseEntity.ok(SessionMapper.toDtoList(savedSessions));
    }
    // Lấy tất cả buổi học của lớp
        @GetMapping("/class/{classId}")
         public ResponseEntity<List<Session>> getSessionsByClass(@PathVariable Long classId) throws UserExcepion {
        ClassEmtity findClass = classRepository.findById(classId)
                .orElseThrow(() -> new UserExcepion("Class not found"));
        return ResponseEntity.ok(findClass.getSessions());
        }

        @GetMapping("/qr/{sessionId}")
        public ResponseEntity<ApiResponse> generateQRCodeLocal(@PathVariable Long sessionId) throws UserExcepion, Exception {
            String base64Qr = sessionService.generateQRCodeLocal(sessionId); // sửa service để trả String
            ApiResponse api = new ApiResponse();
            api.setMessage("QR generated successfully");
            api.setData(base64Qr);
            return ResponseEntity.ok(api);
        }

}
