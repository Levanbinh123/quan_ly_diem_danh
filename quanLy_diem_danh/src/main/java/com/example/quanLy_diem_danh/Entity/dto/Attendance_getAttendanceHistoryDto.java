package com.example.quanLy_diem_danh.Entity.dto;

import com.example.quanLy_diem_danh.Entity.emum_.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance_getAttendanceHistoryDto {
    private Long id;
    private String className;
    private LocalDateTime sessionDate;
    private String studentName;
    private AttendanceStatus status;
}
