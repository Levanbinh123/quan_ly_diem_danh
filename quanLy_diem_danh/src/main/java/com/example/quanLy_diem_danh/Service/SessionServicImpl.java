package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.Images;
import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.dto.SessionDto;
import com.example.quanLy_diem_danh.Entity.mapper.ClassEntityMapper;
import com.example.quanLy_diem_danh.Entity.mapper.SessionMapper;
import com.example.quanLy_diem_danh.Repository.ClassEntityRepository;
import com.example.quanLy_diem_danh.Repository.ImagesRepository;
import com.example.quanLy_diem_danh.Repository.SessionRepository;
import com.example.quanLy_diem_danh.Service.service.SessionService;
import com.example.quanLy_diem_danh.configuration.Excception.UserExcepion;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class SessionServicImpl implements SessionService {
        @Autowired
        private SessionRepository sessionRepository;
        @Autowired
        private ClassEntityRepository classEntityRepository;
        @Autowired
        private ImagesRepository imagesRepository;
    @Override
    public List<Session> createSession(List<SessionDto> sessiondto, Long classId) throws UserExcepion, Exception {
        ClassEmtity findClass = classEntityRepository.findById(classId)
                .orElseThrow(() -> new UserExcepion("Class not found"));

        List<Session> sessionList = new ArrayList<>();
        Integer length= sessionList.size();
        for (SessionDto dto : sessiondto) {
            Session session = new Session();
            session.setClassz(findClass);
            session.setSessionNumber(dto.getSessionNumber());
            session.setDate(dto.getDate() != null ? dto.getDate() : LocalDateTime.now());
            session.setStartTime(dto.getStartTime());
            session.setEndTime(dto.getEndTime());
            session.setRoom(dto.getRoom());
            session.setStatus(dto.getStatus());
            sessionList.add(session);
        }
        List<Session> savedSessions = sessionRepository.saveAll(sessionList);
// Thêm vào lớp
        findClass.getSessions().addAll(savedSessions);
        classEntityRepository.save(findClass);
        return savedSessions;

    }
    @Override
    public String generateQRCodeLocal( Long id) throws Exception {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buổi học không tồn tại"));

        String checkInUrl = "http://localhost:8080/api/attendance/checkin?sessionId="+session.getId();
        int width = 250, height = 250;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(checkInUrl, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);

        byte[] qrBytes = baos.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(qrBytes);
        // Cập nhật session
        session.setQrExpireTime(LocalDateTime.now().plusDays(1));
        session.setQrToken(UUID.randomUUID().toString()); // random token an toàn hơn
        sessionRepository.save(session);
        Images images=new Images();
        images.setDuLieuAnh(base64Image);
        images.setSession(session);
        images.setDuongDan(null);
        imagesRepository.save(images);
        return base64Image;
    }
}
