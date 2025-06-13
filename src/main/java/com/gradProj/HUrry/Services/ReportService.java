package com.gradProj.HUrry.Services;

import com.gradProj.HUrry.Dto.ReportDTO;
import com.gradProj.HUrry.Repositories.ReportRepository;
import com.gradProj.HUrry.entity.Report;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = convertToEntity(reportDTO);
        Report savedReport = reportRepository.save(report);
        return convertToDTO(savedReport);
    }

    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getReportsByCategory(String category) {
        return reportRepository.findByCategory(category)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getReportsByLocation(String locationType) {
        return reportRepository.findByLocationType(locationType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getReportsByCategoryAndLocation(String category, String locationType) {
        return reportRepository.findByCategoryAndLocationType(category, locationType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getReportsByStudentEmail(String email) {
        return reportRepository.findByStudentEmail(email)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReportDTO updateReportStatus(Long id, String status) {
        Report report = reportRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Report not found"));
        report.setStatus(status);
        Report updatedReport = reportRepository.save(report);
        return convertToDTO(updatedReport);
    }

    private ReportDTO convertToDTO(Report report) {
        return modelMapper.map(report, ReportDTO.class);
    }

    private Report convertToEntity(ReportDTO reportDTO) {
        return modelMapper.map(reportDTO, Report.class);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
//    private final MissingItemRepo missingItemRepo;
//
//    public ReportService(MissingItemRepo missingItemRepo, MissingItemRepo missingItemRepo1) {
//        this.missingItemRepo = missingItemRepo1;
//    }
//
//    public MissingItemDto reportNewMissingItem(MissingItemDto missingItemDto) {
//        Report missingItem = new Report();
//        missingItem.setReportedMissingDate(LocalDate.now());
//        miss
//
//        return missingItemDto;
//
//    }
//
//    @Autowired
//    private ReportRepository reportRepository;
//
//    public Report createReport(Report report) {
//        return reportRepository.save(report);
//    }
//
//    public List<Report> getAllReports() {
//        return reportRepository.findAll();
//    }
//
//    public List<Report> getReportsByCategory(String category) {
//        return reportRepository.findByCategory(category);
//    }
//
//    public List<Report> getReportsByLocation(String locationType) {
//        return reportRepository.findByLocationType(locationType);
//    }
//
//    public List<Report> getReportsByCategoryAndLocation(String category, String locationType) {
//        return reportRepository.findByCategoryAndLocationType(category, locationType);
//    }
//
//    public List<Report> getReportsByStudentEmail(String email) {
//        return reportRepository.findByStudentEmail(email);
//    }
//
//    public Report updateReportStatus(Long id, String status) {
//        Report report = reportRepository.findById(id).orElseThrow(() ->
//                new RuntimeException("Report not found"));
//        report.setStatus(status);
//        return reportRepository.save(report);
//    }


}
