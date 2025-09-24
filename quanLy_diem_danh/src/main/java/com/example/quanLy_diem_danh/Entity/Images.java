package com.example.quanLy_diem_danh.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hinh_anh")
    private int maHinhAnh;
    @Column(name = "duong_dan")
    private String duongDan;
    @Column(name = "du_lieu_anh",columnDefinition = "LONGTEXT")
    @Lob
    private String duLieuAnh;
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}