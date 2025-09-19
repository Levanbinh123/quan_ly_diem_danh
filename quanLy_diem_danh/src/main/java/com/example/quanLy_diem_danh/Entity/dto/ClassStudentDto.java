package com.example.quanLy_diem_danh.Entity.dto;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.emum_.Stutus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ClassStudentDto {
    private Long id;
    private ClassEntityDto clasz;
    private Stutus stutus=Stutus.Active;
    private UserDto student;
}
