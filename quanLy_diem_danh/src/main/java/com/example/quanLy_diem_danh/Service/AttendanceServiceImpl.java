package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Entity.Attendance;
import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.AdminDto.AttendanceSummaryDTO;
import com.example.quanLy_diem_danh.Entity.dto.AttendanceDto;
import com.example.quanLy_diem_danh.Entity.dto.Attendance_getAttendanceHistoryDto;
import com.example.quanLy_diem_danh.Entity.emum_.AttendanceStatus;
import com.example.quanLy_diem_danh.Repository.AttendanceRepository;
import com.example.quanLy_diem_danh.Repository.SessionRepository;
import com.example.quanLy_diem_danh.Repository.UserRepository;
import com.example.quanLy_diem_danh.Service.service.AttendanceService;
import com.example.quanLy_diem_danh.Service.service.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public Attendance checkin(Long sessionId, Long studentId) throws Exception {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Buổi học không tồn tại"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Bạn không phải học sinh trường này"));

        if (session.getQrExpireTime() != null && LocalDateTime.now().isAfter(session.getQrExpireTime())) {
            throw new Exception("Mã QR đã hết hạn");
        }

        boolean exists = attendanceRepository.existsBySessionIdAndStudentId(sessionId, studentId);
        if (exists) {
            throw new Exception("Bạn đã điểm danh rồi");
        }

        LocalDateTime now = LocalDateTime.now();
        long minutes = Duration.between(session.getStartTime(), now).toMinutes();

        Attendance attendance = new Attendance();
        attendance.setSession(session);
        attendance.setCreatedAt(now);
        attendance.setMethod("qr_code");
        if (minutes <= 10) {
            attendance.setStatus(AttendanceStatus.PRESENT);
        } else if (minutes <= 20) {
            attendance.setStatus(AttendanceStatus.LATE);
        } else {
            attendance.setStatus(AttendanceStatus.ABSENT);
        }
        attendance.setStudent(student);
        attendance.setTimeChecked(now);

        attendanceRepository.save(attendance);
        return attendance;
    }
    @Override
    public List<User> findStudentsByStatus(Long sessionId, AttendanceStatus status) {
        Optional<Session> findSession=sessionRepository.findById(sessionId);
        Session session=findSession.get();
        List<Attendance> attendances = attendanceRepository.findBySessionAndStatus(session, status);
        return attendances.stream()
                .map(Attendance::getStudent)
                .collect(Collectors.toList());
    }
    public Map<String, Map<String, Long>> getWeeklyStats() {
        List<Object[]> rows = attendanceRepository.countAttendanceByWeek();
        Map<String, Map<String, Long>> result = new HashMap<>();

        for (Object[] row : rows) {
            int year = (int) row[0];
            int week = (int) row[1];
            String status = row[2].toString();
            Long count = (Long) row[3];

            String key = year + "-W" + week;
            result.putIfAbsent(key, new HashMap<>());
            result.get(key).put(status, count);
        }
        return result;
    }

    @Override
    public Attendance updateAttendanceStatus(Long id,Attendance attendance) throws Exception {
        Optional<Attendance> find=attendanceRepository.findById(id);
        if (!find.isPresent()) {
            throw new Exception("not found ");
        }
            Attendance attendanceBeUpdate = find.get();
        attendanceBeUpdate.setStatus(attendance.getStatus());
        attendanceBeUpdate.setCreatedAt(LocalDateTime.now());
        attendanceRepository.save(attendanceBeUpdate);
            return attendanceBeUpdate;
    }

    @Override
    public void deleteAttendance(Long attendanceId) throws Exception {
        Optional<Attendance> findAttendance=attendanceRepository.findById(attendanceId);
        if (!findAttendance.isPresent()) {
            throw new Exception("notfound");
        }
        attendanceRepository.delete(findAttendance.get());

    }

    @Override
    public Map<String, Object> resultAttendances(Long studentId) {
        List<Attendance> attendances = attendanceRepository.findByStudentId(studentId);
        Integer sum=attendances.size();
        Long persent=attendances.stream().filter(dd->dd.getStatus()==AttendanceStatus.PRESENT).count();
        Long late=attendances.stream().filter(dd->dd.getStatus()==AttendanceStatus.LATE).count();
        Long absent=attendances.stream().filter(dd->dd.getStatus()==AttendanceStatus.ABSENT).count();
        Map<String, Object> result = new HashMap<>();
        result.put("Tong so buoi", sum);
        result.put("Persent", persent);
        result.put("Late", late);
        result.put("Absent", absent);
        result.put("ti le chuyencan",sum==0?"0%":(persent*100.0/sum)+"%");
        return result;
    }
    public Map<String, Object> getAttendanceHistory(Long studentId) {
        List<Attendance> attendances = attendanceRepository.findByStudentId(studentId);
        List<Attendance_getAttendanceHistoryDto> dtoList = attendances.stream().map(a -> new Attendance_getAttendanceHistoryDto(
                a.getId(),
                a.getSession().getClassz().getCode(),
                a.getSession().getDate(),
                a.getStudent().getFullName(),
                a.getStatus()
        )).toList();
        Integer sum=dtoList.size();
        Long persent=dtoList.stream().filter(dd->dd.getStatus()==AttendanceStatus.PRESENT).count();
        Long late=dtoList.stream().filter(dd->dd.getStatus()==AttendanceStatus.LATE).count();
        Long absent=dtoList.stream().filter(dd->dd.getStatus()==AttendanceStatus.ABSENT).count();
        Map<String, Object> result = new HashMap<>();
        result.put("Tong so buoi", sum);
        result.put("Persent", persent);
        result.put("Late", late);
        result.put("Absent", absent);
        result.put("tilechuyencan",sum==0?"0%":(persent*100.0/sum)+"%");
        result.put("details ",dtoList);
        return result;
    }

    @Override
    public List<AttendanceSummaryDTO> getAttendanceSummary(Long classId) {
        List<Attendance>list=attendanceRepository.findBySession_ClassId(classId);
        return list.stream().collect(Collectors.groupingBy(a->a.getSession().getDate())).entrySet().stream().map(
                entry->{
                    String date=entry.getKey().toString();
                    List<Attendance> sessionList=entry.getValue();
                    AttendanceSummaryDTO dto = new AttendanceSummaryDTO();
                    dto.setClassName(sessionList.get(0).getSession().getClassz().getName());
                    dto.setSessionDate(date);
                    dto.setSoLuongSinhVien(sessionList.size());
                    dto.setSoLanCoMat(sessionList.stream().filter(a -> a.getStatus() == AttendanceStatus.PRESENT).count());
                    dto.setSoLanMuon(sessionList.stream().filter(a -> a.getStatus() == AttendanceStatus.LATE).count());
                    dto.setSoLanVang(sessionList.stream().filter(a -> a.getStatus() == AttendanceStatus.ABSENT).count());
                    return dto;
                }
        ).toList();
    }
    // 3. Xuất báo cáo PDF
    public byte[] exportReport(Long classId) throws Exception {
        List<AttendanceSummaryDTO> summary = getAttendanceSummary(classId);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        com.itextpdf.text.Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph("Báo cáo điểm danh lớp"));
        document.add(new Paragraph(" "));

        for (AttendanceSummaryDTO dto : summary) {
            document.add(new Paragraph(
                    "Lớp: " + dto.getClassName() +
                            " | Ngày: " + dto.getSessionDate() +
                            " | Có mặt: " + dto.getSoLanCoMat() +
                            " | Muộn: " + dto.getSoLanMuon() +
                            " | Vắng: " + dto.getSoLanVang()
            ));
        }

        document.close();
        return out.toByteArray();
    }

    public Map<String, Map<String, Long>> getMonthlyStats() {
        List<Object[]> rows = attendanceRepository.countAttendanceByMonth();
        Map<String, Map<String, Long>> result = new HashMap<>();

        for (Object[] row : rows) {
            int year = (int) row[0];
            int month = (int) row[1];
            String status = row[2].toString();
            Long count = (Long) row[3];

            String key = year + "-" + month;
            result.putIfAbsent(key, new HashMap<>());
            result.get(key).put(status, count);
        }
        return result;
    }

    // Chạy mỗi phút để set ABSENT cho sinh viên chưa điểm danh sau 20 phút
//    @Scheduled(cron = "0 * * * * ?")
//    public void markAbsentStudents() {
//        LocalDateTime now = LocalDateTime.now();
//        List<Session> sessions = sessionRepository.findAll();
//
//        for (Session session : sessions) {
//            if (session.getStartTime() == null) continue;
//
//            long minutes = Duration.between(session.getStartTime(), now).toMinutes();
//            if (minutes > 20) {
//                List<User> students = s
//                for (User student : students) {
//                    boolean exists = attendanceRepository.existsBySessionIdAndStudentId(session.getId(), student.getId());
//                    if (!exists) {
//                        Attendance attendance = new Attendance();
//                        attendance.setSession(session);
//                        attendance.setStudent(student);
//                        attendance.setCreatedAt(now);
//                        attendance.setMethod("system_auto");
//                        attendance.setStatus(AttendanceStatus.ABSENT);
//                        attendance.setTimeChecked(now);
//                        attendanceRepository.save(attendance);
//                    }
//                }
//            }
//        }
//    }
}
