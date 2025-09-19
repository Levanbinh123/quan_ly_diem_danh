package com.example.quanLy_diem_danh.Service.service;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.dto.ClassEntityDto;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;

public interface ClassEntityService {
    public ClassEmtity createClassEntity(ClassEntityDto teacherClass, String token) throws UserExcepion;
}
