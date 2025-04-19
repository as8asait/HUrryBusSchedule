package com.gradProj.HUrry.Repositories;

import com.gradProj.HUrry.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByCategory(String category);
    List<Report> findByLocationType(String locationType);
    List<Report> findByCategoryAndLocationType(String category, String locationType);
    List<Report> findByStudentEmail(String studentEmail);
}
