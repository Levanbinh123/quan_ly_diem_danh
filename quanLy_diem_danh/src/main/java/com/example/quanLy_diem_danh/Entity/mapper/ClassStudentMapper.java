package com.example.quanLy_diem_danh.Entity.mapper;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.ClassStudent;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.ClassStudentDto;

public class ClassStudentMapper {
    public static ClassStudentDto toDto(ClassStudent classStudent) {
        ClassStudentDto classStudentDto = new ClassStudentDto();
        classStudentDto.setId(classStudent.getId());
        classStudentDto.setClasz(ClassEntityMapper.toDto(classStudent.getClasz()));
        classStudentDto.setStutus(classStudent.getStutus());
        classStudentDto.setStudent(UserMapper.toDto(classStudent.getStudent()));
        return classStudentDto;
    }
    public static ClassStudent toEntity(ClassStudentDto classStudentDto, ClassEmtity classEmtity,User student) {
        ClassStudent classStudent = new ClassStudent();
        classStudent.setId(classStudentDto.getId());
        classStudent.setClasz(classEmtity);
        classStudent.setStutus(classStudentDto.getStutus());
        classStudent.setStudent(student);
        return classStudent;
    }
}
