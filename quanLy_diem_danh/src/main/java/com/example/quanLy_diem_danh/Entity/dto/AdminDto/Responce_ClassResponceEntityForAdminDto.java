package com.example.quanLy_diem_danh.Entity.dto.AdminDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responce_ClassResponceEntityForAdminDto {
    private Long id;
    private String className;
    private String teacherName;
    private int soLuongSinhVien;
}
