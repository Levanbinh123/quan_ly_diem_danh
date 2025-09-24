package com.example.quanLy_diem_danh.Repository;

import com.example.quanLy_diem_danh.Entity.Attendance;
import com.example.quanLy_diem_danh.Entity.Session;
import com.example.quanLy_diem_danh.Entity.emum_.AttendanceStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    boolean existsBySessionIdAndStudentId(Long sessionId, Long studentId);
    List<Attendance> findBySessionAndStatus(Session session, AttendanceStatus status);

    List<Attendance> findByStatus(AttendanceStatus status);
    @Query("SELECT FUNCTION('YEAR', a.timeChecked) as year, " +
            "FUNCTION('WEEK', a.timeChecked) as week, " +
            "a.status, COUNT(a) " +
            "FROM Attendance a " +
            "GROUP BY FUNCTION('YEAR', a.timeChecked), FUNCTION('WEEK', a.timeChecked), a.status")
    List<Object[]> countAttendanceByWeek();

    @Query("SELECT FUNCTION('YEAR', a.timeChecked) as year, " +
            "FUNCTION('MONTH', a.timeChecked) as month, " +
            "a.status, COUNT(a) " +
            "FROM Attendance a " +
            "GROUP BY FUNCTION('YEAR', a.timeChecked), FUNCTION('MONTH', a.timeChecked), a.status")
    List<Object[]> countAttendanceByMonth();
    List<Attendance> findByStudentId(Long studentId);
    @Query("select a from Attendance  a where a.session.classz.id=:classId")
    List<Attendance> findBySession_ClassId(Long classId);
}
