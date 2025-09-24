package com.example.quanLy_diem_danh.Entity.dto.AdminDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSummaryDTO {
    private String className;
    private String sessionDate;
    private long soLuongSinhVien;
    private long soLanCoMat;
    private long soLanMuon;
    private long soLanVang;
}
