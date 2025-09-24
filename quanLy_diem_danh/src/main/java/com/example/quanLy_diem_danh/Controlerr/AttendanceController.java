package com.example.quanLy_diem_danh.Controlerr;

import com.example.quanLy_diem_danh.Entity.Attendance;
import com.example.quanLy_diem_danh.Entity.User;
import com.example.quanLy_diem_danh.Entity.dto.AdminDto.AttendanceSummaryDTO;
import com.example.quanLy_diem_danh.Entity.dto.AttendanceDto;
import com.example.quanLy_diem_danh.Entity.dto.Attendance_getAttendanceHistoryDto;
import com.example.quanLy_diem_danh.Entity.emum_.AttendanceStatus;
import com.example.quanLy_diem_danh.Service.service.AttendanceService;
import com.example.quanLy_diem_danh.Service.service.ExportPdfService;
import com.example.quanLy_diem_danh.payload.response.ApiResponse;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    ExportPdfService exportPdfService;
    @Autowired
    private AttendanceService attendanceService;
    @GetMapping("/checkin")
    public ResponseEntity<ApiResponse> checkin(@RequestParam Long sessionId, @RequestParam(required = false) Long studentId) throws Exception {
         attendanceService.checkin(sessionId,studentId);
        ApiResponse api=new ApiResponse();
        api.setMessage("checkin sussessed");
        return new ResponseEntity<>(api, HttpStatus.OK);
    }
    @GetMapping("/status/{sessionId}")
    public ResponseEntity<List<User>> findStudentsByStatus(@PathVariable Long sessionId, @RequestParam AttendanceStatus status){
        return new ResponseEntity<>(attendanceService.findStudentsByStatus(sessionId,status),HttpStatus.OK);

    }
    @GetMapping("/stats/week")
    public ResponseEntity<?> getWeeklyStats() {
        return ResponseEntity.ok(attendanceService.getWeeklyStats());
    }

    @GetMapping("/stats/month")
    public ResponseEntity<?> getMonthlyStats() {
        return ResponseEntity.ok(attendanceService.getMonthlyStats());
    }
    @GetMapping("/stats/week/pdf")
    public ResponseEntity<InputStreamResource> downloadWeeklyPdf() throws DocumentException {
        ByteArrayInputStream in = exportPdfService.exportWeeklyStatsToPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=weekly_stats.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }
    @PatchMapping("/{id}")
    private ResponseEntity<Attendance> updateAttendanceStatus(@PathVariable Long id,@RequestBody Attendance attendance) throws Exception {
        return ResponseEntity.ok(attendanceService.updateAttendanceStatus(id,attendance));
    }
    @GetMapping("/student/{id}/attendances")
    public ResponseEntity<?> getDataAttendanceofOneStudent(@PathVariable Long id) throws Exception{
        Map<String , Object> data=attendanceService.resultAttendances(id);
        return ResponseEntity.ok(data);
    }
    @GetMapping("/student/{id}/history")
    public ResponseEntity<Map<String, Object>> getAttendanceHistory(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.getAttendanceHistory(id));
    }
    @GetMapping("/{id}/Summary")
    public ResponseEntity<List<AttendanceSummaryDTO>>getAttendanceSummary(@PathVariable Long id){
        return ResponseEntity.ok(attendanceService.getAttendanceSummary(id));
    }
    // Xuất báo cáo PDF//can phai dinh dang lai file gui cho dep hon
    @GetMapping("/report/{classId}")
    public ResponseEntity<byte[]> exportReport(@PathVariable Long classId) throws Exception {
        byte[] pdf = attendanceService.exportReport(classId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
