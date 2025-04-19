package com.gradProj.HUrry.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReportDTO {
    private String category;
    private String description;
    private String brand;
    private String model;
    private boolean containsItems;
    private String itemsInside;
    private String uniqueFeatures;
    private LocalDate dateLost;
    private LocalDate timeApproximate;
    private String locationType;
    private String station;
    private String busNumber;
    private List<String> photoUrls;
    private String studentName;
    private String studentEmail;


}
