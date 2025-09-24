package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.ClassStudent;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.emum_.Stutus;
import com.example.quanLy_diem_danh.Repository.ClassEntityRepository;
import com.example.quanLy_diem_danh.Repository.ClassStudentRepository;
import com.example.quanLy_diem_danh.Service.service.ClassStudentService;
import com.example.quanLy_diem_danh.Service.service.UserService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassStudentServiceImpl implements ClassStudentService {
    @Autowired
    private ClassStudentRepository classStudentRepository;
    @Autowired
    private ClassEntityRepository classEntityRepository;
    @Autowired
    private UserService userService;
    @Override
    public ClassStudent registerClass(Long classId, String token) throws UserExcepion, Exception {
        User student=userService.getUserByToken(token);
        ClassEmtity clasz = classEntityRepository.findById(classId)
                .orElseThrow(() -> new Exception("Lớp không tồn tại"));
        // Kiểm tra hạn đăng ký
        if (clasz.getRegistrationDeadline() != null &&
                LocalDateTime.now().isAfter(clasz.getRegistrationDeadline())) {
            throw new Exception("Đã hết hạn đăng ký lớp này");
        }

        // Kiểm tra số lượng tối đa
        long count = classStudentRepository.countByClaszIdAndStutus(classId, Stutus.Active);
        if (clasz.getMaxStudents() != null && count >= clasz.getMaxStudents()) {
            throw new Exception("Lớp đã đầy");
        }
        // Kiểm tra đã đăng ký chưa
        if (classStudentRepository.existsByClaszIdAndStudentId(classId, student.getId())) {
            throw new Exception("Bạn đã đăng ký lớp này rồi");
        }
        ClassStudent classStudent = new ClassStudent();
        classStudent.setClasz(clasz);
        classStudent.setStudent(student);
        classStudent.setStutus(Stutus.Active);

        return classStudentRepository.save(classStudent);
    }

    @Override
    public List<ClassEmtity> getClasses(String token) throws UserExcepion, Exception {
        User student=userService.getUserByToken(token);
        List<ClassStudent> classStudents = classStudentRepository.findByStudentId(student.getId());
        return classStudents.stream()
                .map(ClassStudent::getClasz)
                .toList();
    }

}
