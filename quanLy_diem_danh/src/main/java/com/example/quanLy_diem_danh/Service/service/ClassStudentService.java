package com.example.quanLy_diem_danh.Service.service;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.ClassStudent;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;

import java.util.List;

public interface ClassStudentService {
    public ClassStudent registerClass(Long classId, String token) throws UserExcepion, Exception;
    public List<ClassEmtity> getClasses(String token) throws UserExcepion, Exception;
}
