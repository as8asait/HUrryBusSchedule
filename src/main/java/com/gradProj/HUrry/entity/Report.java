package com.gradProj.HUrry.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String description;
    private String brand;
    private String model;
    private boolean containsItems; // 4 bag or purse
    private String itemsInside;
    private String uniqueFeatures;


    private LocalDate dateLost;

    private LocalTime timeApproximate;

    private LocalDate dateSubmitted;// initialize to current date

    private LocalDate foundMissingDate;

    private String locationType; // station, bus, etc.
    private String station;
    private String busNumber;

    @ElementCollection
    private List<String> photoUrls;

    private String status = "pending";
    private String studentName;
    private String studentEmail;
    private String phoneNumber;

}
