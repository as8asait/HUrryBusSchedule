package com.gradProj.HUrry.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Trip {
    @Id
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_seq")
    public Long id;
    public String route;
    @Transient
    public Bus bus;
    public String time;
    public String Station;

//    public Trip(long id, String route, Bus bus, String time) {
//        this.id = id;
//        this.route = route;
//        this.bus = bus;
//        this.time = time;
//    }
}
