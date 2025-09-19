package com.example.quanLy_diem_danh.Controlerr;

import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.dto.ClassEntityDto;
import com.example.quanLy_diem_danh.Entity.dto.SessionDto;
import com.example.quanLy_diem_danh.Service.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    @PostMapping("/class/{classId}")
    public ResponseEntity<List<Session>> createSession(@RequestBody SessionDto session, @PathVariable Long classId){
        return ResponseEntity.ok(sessionService.createSession(session,classId));
    }
}
