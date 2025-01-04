package com.gradProj.HUrry.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String route;
    @Transient
    public Bus bus;
    public String time;
    public String station;

//    public Trip(long id, String route, Bus bus, String time) {
//        this.id = id;
//        this.route = route;
//        this.bus = bus;
//        this.time = time;
//    }
}
