package com.example.quanLy_diem_danh.Entity;

import com.example.quanLy_diem_danh.Entity.emum_.AttendanceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Session session;
    @ManyToOne
    private User student;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
    private LocalDateTime timeChecked;
    private String method;
    private LocalDateTime createdAt;

}
