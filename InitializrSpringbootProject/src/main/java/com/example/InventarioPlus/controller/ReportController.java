package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.service.ReportService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getReport(
            @PathVariable("name") String name,
            @RequestParam(name = "format", defaultValue = "pdf") String format) {
        try {
            Map<String, Object> params = new HashMap<>();
            byte[] data = reportService.generateReport(name, format, params);
            HttpHeaders headers = new HttpHeaders();
            String filename = name + "." + ("xlsx".equalsIgnoreCase(format) ? "xlsx" : "pdf");
            if ("pdf".equalsIgnoreCase(format)) {
                headers.setContentType(MediaType.APPLICATION_PDF);
            } else {
                headers.setContentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            }
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage().getBytes());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage().getBytes());
        }
    }

}
