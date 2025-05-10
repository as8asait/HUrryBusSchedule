package com.gradProj.HUrry.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String station_name;
    private double lat;
    private double lon;
    private double distance;
    private double time;
}
