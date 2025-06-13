package com.gradProj.HUrry.Controllers;

import com.gradProj.HUrry.Dto.ReportDTO;
import com.gradProj.HUrry.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/missingReport")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {
        return ResponseEntity.ok(reportService.createReport(reportDTO));
    }

    @GetMapping
    @PreAuthorize("hasRole('OPERATOR')&&('STUDENT')")
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasRole('OPERATOR')")
    public ResponseEntity<List<ReportDTO>> getReportsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(reportService.getReportsByCategory(category));
    }

    @GetMapping("/location/{locationType}")
    @PreAuthorize("hasRole('OPERATOR')")
    public ResponseEntity<List<ReportDTO>> getReportsByLocation(@PathVariable String locationType) {
        return ResponseEntity.ok(reportService.getReportsByLocation(locationType));
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('OPERATOR')")
    public ResponseEntity<List<ReportDTO>> searchReports(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String locationType) {

        if (category != null && locationType != null) {
            return ResponseEntity.ok(reportService.getReportsByCategoryAndLocation(category, locationType));
        } else if (category != null) {
            return ResponseEntity.ok(reportService.getReportsByCategory(category));
        } else if (locationType != null) {
            return ResponseEntity.ok(reportService.getReportsByLocation(locationType));
        } else {
            return ResponseEntity.ok(reportService.getAllReports());
        }
    }

    @GetMapping("/my-reports")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<ReportDTO>> getMyReports(@RequestParam String email) {
        return ResponseEntity.ok(reportService.getReportsByStudentEmail(email));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('OPERATOR')")
    public ResponseEntity<ReportDTO> updateReportStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(reportService.updateReportStatus(id, status));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReport(@PathVariable Long id) {
       reportService.deleteReport(id);
    }

//    @PostMapping
//    @PreAuthorize("hasRole('REPORTER')")
//    public ResponseEntity<Report> createReport(@RequestBody Report report) {
//        return ResponseEntity.ok(reportService.createReport(report));
//    }
//
//    @GetMapping
//    @PreAuthorize("hasRole('VIEWER')")
//    public ResponseEntity<List<Report>> getAllReports() {
//        return ResponseEntity.ok(reportService.getAllReports());
//    }
//
//    @GetMapping("/category/{category}")
//    @PreAuthorize("hasRole('VIEWER')")
//    public ResponseEntity<List<Report>> getReportsByCategory(@PathVariable String category) {
//        return ResponseEntity.ok(reportService.getReportsByCategory(category));
//    }
//
//    @GetMapping("/location/{locationType}")
//    @PreAuthorize("hasRole('VIEWER')")
//    public ResponseEntity<List<Report>> getReportsByLocation(@PathVariable String locationType) {
//        return ResponseEntity.ok(reportService.getReportsByLocation(locationType));
//    }
//
//    @GetMapping("/search")
//    @PreAuthorize("hasRole('VIEWER')")
//    public ResponseEntity<List<Report>> searchReports(
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) String locationType) {
//
//        if (category != null && locationType != null) {
//            return ResponseEntity.ok(reportService.getReportsByCategoryAndLocation(category, locationType));
//        } else if (category != null) {
//            return ResponseEntity.ok(reportService.getReportsByCategory(category));
//        } else if (locationType != null) {
//            return ResponseEntity.ok(reportService.getReportsByLocation(locationType));
//        } else {
//            return ResponseEntity.ok(reportService.getAllReports());
//        }
//    }
//
//    @GetMapping("/my-reports")
//    @PreAuthorize("hasRole('REPORTER')")
//    public ResponseEntity<List<Report>> getMyReports(@RequestParam String email) {
//        return ResponseEntity.ok(reportService.getReportsByStudentEmail(email));
//    }
//
//    @PatchMapping("/{id}/status")
//    @PreAuthorize("hasRole('VIEWER')")
//    public ResponseEntity<Report> updateReportStatus(
//            @PathVariable Long id,
//            @RequestParam String status) {
//        return ResponseEntity.ok(reportService.updateReportStatus(id, status));
//    }
}


