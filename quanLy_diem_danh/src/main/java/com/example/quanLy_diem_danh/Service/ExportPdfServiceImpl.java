package com.example.quanLy_diem_danh.Service;

import com.example.quanLy_diem_danh.Service.service.AttendanceService;
import com.example.quanLy_diem_danh.Service.service.ExportPdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
public class ExportPdfServiceImpl implements ExportPdfService {
    @Autowired
    private AttendanceService attendanceService;
    @Override
    public ByteArrayInputStream exportWeeklyStatsToPdf() throws DocumentException {
        Map<String, Map<String, Long>> data = attendanceService.getWeeklyStats();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Paragraph title = new Paragraph("Weekly Attendance Statistics", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        PdfPTable    table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.addCell("Week");
        table.addCell("Status");
        table.addCell("Count");
        for (var entry : data.entrySet()) {
            String week = entry.getKey();
            for (var statusEntry : entry.getValue().entrySet()) {
                table.addCell(week);
                table.addCell(statusEntry.getKey());
                table.addCell(statusEntry.getValue().toString());
            }
        }
        document.add(table);
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
