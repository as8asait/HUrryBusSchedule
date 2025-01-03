package com.gradProj.HUrry.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class BusSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberOfBuses;
    private String arrivalTime;
    private String departureTime;
    private String driverName;
    private String driverEmail;
}
