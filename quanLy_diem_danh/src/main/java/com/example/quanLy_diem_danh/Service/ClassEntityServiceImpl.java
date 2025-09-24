package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.AdminDto.Responce_ClassResponceEntityForAdminDto;
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

import java.util.List;

@Service
public class ClassEntityServiceImpl implements ClassEntityService {
    @Autowired
    private ClassEntityRepository classRepository;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ClassEmtity createClassEntity(ClassEntityDto teacherClass,Long id) throws UserExcepion {
        User user = userRepository.findById(id).get();
        // Gán teacher trực tiếp vào DTO
        ClassEmtity classEntityDto = new ClassEmtity();
        classEntityDto.setName(teacherClass.getName());
        classEntityDto.setDescription(teacherClass.getDescription());
        classEntityDto.setCode(teacherClass.getCode());
        classEntityDto.setTotalSession(teacherClass.getTotalSession());
        classEntityDto.setStartTime(teacherClass.getStartTime());
        classEntityDto.setEndTime(teacherClass.getEndTime());
        classEntityDto.setTeacher(user);
       return classRepository.save(classEntityDto);

    }

    @Override
    public ClassEmtity findClassByTeacherId(Long id) {
        return classRepository.findClassByTeacherId(id);
    }

    @Override
    public List<Responce_ClassResponceEntityForAdminDto> getAllClasses() {
        return classRepository.findAll().stream().map(c -> {
            Responce_ClassResponceEntityForAdminDto dto = new Responce_ClassResponceEntityForAdminDto();
            dto.setId(c.getId());
            dto.setClassName(c.getName());
            dto.setTeacherName(c.getTeacher() != null ? c.getTeacher().getFullName() : "Chưa phân công");
            dto.setSoLuongSinhVien(c.getStudents().size());
            return dto;
        }).toList();
    }

    @Override
    public void deleteClassById(Long id) throws Exception {
        ClassEmtity findClass=classRepository.findById(id).get();
        if(findClass!=null) {
            classRepository.deleteById(id);
        }
        throw new Exception("not found");


    }

}
