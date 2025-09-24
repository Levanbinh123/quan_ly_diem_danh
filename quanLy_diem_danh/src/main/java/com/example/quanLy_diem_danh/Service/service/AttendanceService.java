package com.example.quanLy_diem_danh.Service.service;

import com.example.quanLy_diem_danh.Entity.Attendance;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.AdminDto.AttendanceSummaryDTO;
import com.example.quanLy_diem_danh.Entity.dto.AttendanceDto;
import com.example.quanLy_diem_danh.Entity.dto.Attendance_getAttendanceHistoryDto;
import com.example.quanLy_diem_danh.Entity.emum_.AttendanceStatus;

import java.util.List;
import java.util.Map;

public interface AttendanceService {
    public Attendance checkin(Long sessionId, Long studentId) throws Exception;
    public List<User> findStudentsByStatus(Long sessionId, AttendanceStatus status);
    public Map<String, Map<String, Long>> getMonthlyStats();
    public Map<String, Map<String, Long>> getWeeklyStats();
    public Attendance updateAttendanceStatus(Long id,Attendance attendance) throws Exception;
    public void deleteAttendance(Long attendanceId) throws Exception;
    public Map<String, Object> resultAttendances(Long hocSinhId);
    //admin
    Map<String, Object> getAttendanceHistory(Long studentId);
    public List<AttendanceSummaryDTO> getAttendanceSummary(Long classId);
    public byte[] exportReport(Long classId) throws Exception;

}
