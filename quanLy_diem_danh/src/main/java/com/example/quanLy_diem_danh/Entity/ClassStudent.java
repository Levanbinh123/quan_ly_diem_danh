package com.example.quanLy_diem_danh.Entity;

import com.example.quanLy_diem_danh.Entity.emum_.Stutus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private ClassEmtity clasz;
    @Enumerated(EnumType.STRING)
    private Stutus stutus=Stutus.Active;
    @ManyToOne(fetch = FetchType.EAGER)
    private User student;
}
