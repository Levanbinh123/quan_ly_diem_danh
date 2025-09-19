package com.example.quanLy_diem_danh.Entity.dto;

import com.example.quanLy_diem_danh.Entity.ClassStudent;
import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class ClassEntityDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private UserDto teacher;
    private Integer totalSession;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<ClassStudentDto> students;
    private List<SessionDto> sessions;
}
