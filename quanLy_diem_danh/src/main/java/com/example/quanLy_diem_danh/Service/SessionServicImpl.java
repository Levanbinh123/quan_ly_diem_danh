package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Entity.ClassEmtity;
import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.dto.SessionDto;
import com.example.quanLy_diem_danh.Entity.mapper.ClassEntityMapper;
import com.example.quanLy_diem_danh.Entity.mapper.SessionMapper;
import com.example.quanLy_diem_danh.Repository.ClassEntityRepository;
import com.example.quanLy_diem_danh.Repository.SessionRepository;
import com.example.quanLy_diem_danh.Service.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServicImpl implements SessionService {
        @Autowired
        private SessionRepository sessionRepository;
        @Autowired
        private ClassEntityRepository classEntityRepository;
    @Override
    public List<Session> createSession(SessionDto session, Long classId) {
        ClassEmtity findClass=classEntityRepository.findById(classId).get();
        List<SessionDto> sessions=new ArrayList<>();
        for(SessionDto sessionDtos:sessions){
            SessionDto sessionDto = new SessionDto();
            sessionDto.setId(session.getId());
            sessionDto.setClassz(ClassEntityMapper.toDto(findClass));
            sessionDto.setDate(LocalDateTime.now());
            sessionDto.setSessionNumber(session.getSessionNumber());
            sessionDto.setStartTime(session.getStartTime());
            sessionDto.setEndTime(session.getEndTime());
            sessionDto.setRoom(session.getRoom());
            sessionDto.setQrToken(session.getQrToken());
            sessionDto.setQrExpireTime(session.getQrExpireTime());
            sessionDto.setStatus(session.getStatus());
            sessions.add(sessionDto);
        }
        List<Session> saved=sessionRepository.saveAll(SessionMapper.fromDtoList(sessions));
        findClass.setSessions(SessionMapper.fromDtoList(sessions));
        classEntityRepository.save(findClass);
        return saved;
    }
}
