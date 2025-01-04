package com.gradProj.HUrry.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long station_id;
    private String station_name;
}
