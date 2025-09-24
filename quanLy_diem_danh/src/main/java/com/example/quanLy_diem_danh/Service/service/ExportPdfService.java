package com.example.quanLy_diem_danh.Service.service;

import com.itextpdf.text.DocumentException;

import java.io.ByteArrayInputStream;

public interface ExportPdfService {
    public ByteArrayInputStream exportWeeklyStatsToPdf() throws DocumentException;
}
