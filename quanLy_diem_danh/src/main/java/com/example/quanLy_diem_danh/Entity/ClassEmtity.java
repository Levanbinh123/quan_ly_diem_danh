package com.example.quanLy_diem_danh.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassEmtity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;
    @ManyToOne
    private User teacher;
    private Integer totalSession;
    private LocalDateTime startTime;
    private Integer maxStudents; //sinh vien toi da
    private LocalDateTime registrationDeadline; // hạn đăng ký
    private LocalDateTime endTime;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ClassStudent> students;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    private List<Session> sessions;
}
