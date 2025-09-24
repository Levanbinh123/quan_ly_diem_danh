package com.example.quanLy_diem_danh.Repository;

import com.example.quanLy_diem_danh.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

}
