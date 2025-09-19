package com.example.quanLy_diem_danh.Entity.mapper;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.dto.ClassEntityDto;

public class ClassEntityMapper {
    public static ClassEntityDto toDto(ClassEmtity classEmtity) {
        ClassEntityDto classEntityDto = new ClassEntityDto();
        classEntityDto.setId(classEmtity.getId());
        classEntityDto.setCode(classEmtity.getCode());
        classEntityDto.setName(classEmtity.getName());
        classEntityDto.setDescription(classEmtity.getDescription());
        classEntityDto.setTeacher(UserMapper.toDto(classEmtity.getTeacher()));
        classEntityDto.setEndTime(classEmtity.getEndTime());
        classEntityDto.setStartTime(classEmtity.getStartTime());
       // classEntityDto.setStudents(UserMapper.toDto(classEmtity.getStudents()));
        return classEntityDto;
    }
    public static ClassEmtity toEntity(ClassEntityDto classEntityDto) {
        ClassEmtity classEmtity = new ClassEmtity();
        classEmtity.setId(classEntityDto.getId());
        classEmtity.setCode(classEntityDto.getCode());
        classEmtity.setName(classEntityDto.getName());
        classEmtity.setDescription(classEntityDto.getDescription());
        classEmtity.setTeacher(UserMapper.toEntity(classEntityDto.getTeacher()));
        classEmtity.setEndTime(classEntityDto.getEndTime());
        classEmtity.setStartTime(classEntityDto.getStartTime());
        return classEmtity;
    }
}
