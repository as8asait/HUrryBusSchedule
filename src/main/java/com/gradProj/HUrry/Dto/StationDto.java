package com.gradProj.HUrry.Dto;

import lombok.Data;

@Data
public class StationDto {
    private String station_name;
    private double lat;
    private double lon;
    private double distance;
    private double time;
}
