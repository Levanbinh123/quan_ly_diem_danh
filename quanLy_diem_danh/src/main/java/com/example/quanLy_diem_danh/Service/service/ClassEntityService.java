package com.example.quanLy_diem_danh.Service.service;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.dto.AdminDto.Responce_ClassResponceEntityForAdminDto;
import com.example.quanLy_diem_danh.Entity.dto.ClassEntityDto;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;

import java.util.List;

public interface ClassEntityService {
    public ClassEmtity createClassEntity(ClassEntityDto teacherClass, Long id) throws UserExcepion;
    public ClassEmtity findClassByTeacherId(Long id);
    public List<Responce_ClassResponceEntityForAdminDto> getAllClasses();
    public void deleteClassById(Long id) throws Exception;
}
