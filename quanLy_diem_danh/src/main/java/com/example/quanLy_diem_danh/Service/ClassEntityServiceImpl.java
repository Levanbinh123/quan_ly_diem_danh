package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.ClassEntityDto;
import com.example.quanLy_diem_danh.Entity.mapper.ClassEntityMapper;
import com.example.quanLy_diem_danh.Entity.mapper.UserMapper;
import com.example.quanLy_diem_danh.Repository.ClassEntityRepository;
import com.example.quanLy_diem_danh.Repository.UserRepository;
import com.example.quanLy_diem_danh.Service.service.ClassEntityService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.example.quanLy_diem_danh.configuration.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassEntityServiceImpl implements ClassEntityService {
    @Autowired
    private ClassEntityRepository classRepository;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ClassEmtity createClassEntity(ClassEntityDto teacherClass, Long teacherId) throws UserExcepion {

        // Gán teacher trực tiếp vào DTO
        ClassEntityDto classEntityDto = new ClassEntityDto();
        classEntityDto.setTeacher(UserMapper.toDto(userRepository.findByEmail(email)));
       return classRepository.save(ClassEntityMapper.toEntity(classEntityDto));

    }
}
