package com.gradProj.HUrry.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.gradProj.HUrry.Dto.StationDto;
import com.gradProj.HUrry.Services.StationService;
import com.gradProj.HUrry.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/busStation")
public class StationController {
    @Autowired
    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/all")
    public List<StationDto> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/getStation/{id}")
    public StationDto getStationById(@PathVariable int id) {
        return stationService.getStationById(id);
    }

    @GetMapping("/addStation")
    public StationDto addStation(@RequestBody StationDto stationDto) {
        return stationService.createStation(stationDto);
    }

    @GetMapping("/update/{id}")
    public StationDto updateStation(@PathVariable int id, @RequestBody StationDto stationDto) {
        return stationService.updateStation(id, stationDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStation(@PathVariable int id) {
        stationService.deleteStationById(id);
    }

    @GetMapping("/closest-stations")
    public List<StationDto> getClosestStations(@RequestParam double lat, @RequestParam double lon) throws Exception {
        return stationService.findClosestStations(lat, lon);
    }
//    @GetMapping("/station-address")
//    public String getStationAddress(@RequestParam double lat, @RequestParam double lon) throws Exception {
//        return stationService.reverseGeocode(lat, lon);

//    @GetMapping("/find-station")
//    public JsonNode forwardGeocode(@RequestParam String address) throws Exception {
//        return stationService.forwardGeocode(address);
//    }
//
//    @GetMapping("/route")
//    public String getRoute(@RequestParam double userLat, @RequestParam double userLon,
//                           @RequestParam double stationLat, @RequestParam double stationLon) throws Exception {
//        return stationService.getRoute(userLat, userLon, stationLat, stationLon);
//    }


}
