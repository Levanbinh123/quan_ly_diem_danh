package com.example.quanLy_diem_danh.Entity;

import com.example.quanLy_diem_danh.Entity.emum_.StatusClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private ClassEmtity classz;
    private Integer sessionNumber;
    private LocalDateTime date;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private String room;
    private String qrToken;
    private LocalDateTime qrExpireTime;
    @Enumerated(EnumType.STRING)
    private StatusClass status;
}
