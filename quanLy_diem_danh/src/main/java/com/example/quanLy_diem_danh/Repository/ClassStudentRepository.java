package com.example.quanLy_diem_danh.Repository;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.ClassStudent;
import com.example.quanLy_diem_danh.Entity.emum_.Stutus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jdk.jshell.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassStudentRepository  extends JpaRepository<ClassStudent, Long> {
    Integer countByClaszIdAndStutus(Long classId, Stutus stutus);
   boolean existsByClaszIdAndStudentId(Long classId, Long studentId);
    List<ClassStudent> findByStudentId(Long studentId);
}
