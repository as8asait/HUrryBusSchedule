package com.gradProj.HUrry.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bus_id;

    private String busNumber;
    private String route;
    private String status;

    public void setBusNumber(String busNumber) {

    }
}
