package com.example.quanLy_diem_danh.Service.service;

import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.dto.SessionDto;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;

import java.util.List;

public interface SessionService {
    public List<Session> createSession(List<SessionDto> sessions, Long classId) throws UserExcepion, Exception;
    public String generateQRCodeLocal( Long id) throws Exception;
}
