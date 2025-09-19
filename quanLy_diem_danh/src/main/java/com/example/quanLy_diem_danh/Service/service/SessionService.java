package com.example.quanLy_diem_danh.Service.service;

import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.dto.SessionDto;

import java.util.List;

public interface SessionService {
    public List<Session> createSession(SessionDto session, Long classId);
}
