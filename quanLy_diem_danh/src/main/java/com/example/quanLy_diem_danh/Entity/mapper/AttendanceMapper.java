package com.example.quanLy_diem_danh.Entity.mapper;

import com.example.quanLy_diem_danh.Entity.Attendance;
import com.example.quanLy_diem_danh.Entity.dto.AttendanceDto;

public class AttendanceMapper {
    public static AttendanceDto toDto(Attendance attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setId(attendance.getId());
        attendanceDto.setSession(SessionMapper.toDto(attendance.getSession()));
        attendanceDto.setStudent(UserMapper.toDto(attendance.getStudent()));
        attendanceDto.setTimeChecked(attendance.getTimeChecked());
        attendanceDto.setMethod(attendance.getMethod());
        attendanceDto.setStatus(attendance.getStatus());
        attendanceDto.setCreatedAt(attendance.getCreatedAt());
        return attendanceDto;
    }
    public static Attendance toEntity(AttendanceDto attendanceDto) {
        Attendance attendance = new Attendance();
        attendance.setId(attendanceDto.getId());
        attendance.setSession(SessionMapper.fromDto(attendanceDto.getSession()));
        attendance.setStudent(UserMapper.toEntity(attendanceDto.getStudent()));
        attendance.setTimeChecked(attendanceDto.getTimeChecked());
        attendance.setMethod(attendanceDto.getMethod());
        attendance.setStatus(attendanceDto.getStatus());
        attendance.setCreatedAt(attendanceDto.getCreatedAt());
        return attendance;
    }
}
