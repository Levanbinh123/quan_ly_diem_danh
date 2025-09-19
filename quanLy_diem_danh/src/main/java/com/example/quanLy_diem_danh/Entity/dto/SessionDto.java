package com.example.quanLy_diem_danh.Entity.dto;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.emum_.StatusClass;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SessionDto {
    private Long id;
    private ClassEntityDto classz;
    private Long classId;
    private Integer sessionNumber;
    private LocalDateTime date;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private String room;
    private String qrToken;
    private LocalDateTime qrExpireTime;
    private StatusClass status;
}
