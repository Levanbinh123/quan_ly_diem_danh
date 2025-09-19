package com.example.quanLy_diem_danh.Entity.mapper;

import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.dto.SessionDto;

import java.util.List;
import java.util.stream.Collectors;

public class SessionMapper {
    public static SessionDto toDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setClassId(session.getClassz().getId());
        sessionDto.setRoom(session.getRoom());
        sessionDto.setQrToken(session.getQrToken());
        sessionDto.setStartTime(session.getStartTime());
        sessionDto.setEndTime(session.getEndTime());
        return sessionDto;
    }
    public static Session fromDto(SessionDto sessionDto) {
        Session session = new Session();
        session.setId(sessionDto.getId());
        session.setRoom(sessionDto.getRoom());
        session.setStartTime(sessionDto.getStartTime());
        session.setEndTime(sessionDto.getEndTime());
        session.setQrToken(sessionDto.getQrToken());
        session.setClassz(ClassEntityMapper.toEntity(sessionDto.getClassz()));
        return session;
    }
    // Map List<Entity> → List<DTO>
    public static List<SessionDto> toDtoList(List<Session> sessions) {
        return sessions.stream()
                .map(SessionMapper::toDto)
                .collect(Collectors.toList());
    }

    // Map List<DTO> → List<Entity>
    public static List<Session> fromDtoList(List<SessionDto> sessionDtos) {
        return sessionDtos.stream()
                .map(SessionMapper::fromDto)
                .collect(Collectors.toList());
    }
}
