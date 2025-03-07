package com.gradProj.HUrry.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gradProj.HUrry.Dto.StationDto;
import com.gradProj.HUrry.Repositories.StationRepository;
import com.gradProj.HUrry.entity.Station;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StationService {
    private final StationRepository stationRepo;

    private static final String GEOAPIFY_API_KEY = "c94061a37e1b48bb902f8ca597534b62";
    private static final String REVERSE_GEOCODING_URL = "https://api.geoapify.com/v1/geocode/reverse";
    private static final String ROUTING_URL = "https://api.geoapify.com/v1/routing";


    public StationService(StationRepository stationRepository) {
        this.stationRepo = stationRepository;
    }
    public StationDto createStation(StationDto stationDto) {
        Station station = new Station();
        station.setStation_name(stationDto.getStation_name());
        station.setLat(stationDto.getLat());
        station.setLon(stationDto.getLon());
        stationRepo.save(station);

        return stationDto;
    }

    public List<StationDto> getAllStations() {
       return stationRepo.findAll().stream().map(station -> transferToStationDto(station)).toList();
    }

    public StationDto getStationById(long id) {
        Optional<Station> station = stationRepo.findById(id);
        if (station.isPresent()) {
            return transferToStationDto(station.get());
        }
        return null;
    }

    public StationDto updateStation(long id,StationDto newStationDto) {
        Optional<Station> oldStation = stationRepo.findById(id);
        if (oldStation.isPresent()) {
            Station station = oldStation.get();
            station.setStation_name(newStationDto.getStation_name());
            station.setLat(newStationDto.getLat());
            station.setLon(newStationDto.getLon());
            return transferToStationDto(stationRepo.save(station));
        }
        return null;
    }

    public void deleteStationById(long id) {
        stationRepo.deleteById(id);
    }

    public StationDto transferToStationDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setStation_name(station.getStation_name());
        stationDto.setLat(station.getLat());
        stationDto.setLon(station.getLon());
        return stationDto;
    }

    public static double calculateDistance(double userLat, double userLon, double stationLat, double stationLon) {
        final int R = 6371; // Radius of the Earth in kilometers
        double latDistance = Math.toRadians(stationLat - userLat);
        double lonDistance = Math.toRadians(stationLon - userLon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(stationLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // Distance in meters
    }

    // Find closest stations within 4000 meters
    public List<StationDto> findClosestStations(double userLat, double userLon) {
        List<StationDto> closestStations = new ArrayList<>();
        List<Station> allStations = stationRepo.findAll();

        for (Station station : allStations) {
            double distance = calculateDistance(userLat, userLon, station.getLat(), station.getLon());
            if (distance <= 15000) {
                closestStations.add(transferToStationDto(station,distance);
            }
        }

        return closestStations.stream().sorted(Comparator.comparingDouble(StationDto::getDistance).reversed()).toList();
    }

    private StationDto transferToStationDto( Station station, double distance){
        StationDto stationDto = new StationDto();
        stationDto.setStation_name(station.getStation_name());
        stationDto.setLat(station.getLat());
        stationDto.setLon(station.getLon());
        stationDto.setDistance(distance);
        return stationDto;
    }

    // Reverse geocode to get station address
    public String reverseGeocode(double lat, double lon) throws Exception {
        String url = String.format("%s?lat=%f&lon=%f&apiKey=%s", REVERSE_GEOCODING_URL, lat, lon, GEOAPIFY_API_KEY);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            String response = EntityUtils.toString(httpClient.execute(request).getEntity());
            JsonNode jsonNode = new ObjectMapper().readTree(response);
            return jsonNode.path("features").get(0).path("properties").path("formatted").asText();
        }
    }

    // Get route from user location to station
    public String getRoute(double userLat, double userLon, double stationLat, double stationLon) throws Exception {
        String url = String.format("%s?waypoints=%f,%f|%f,%f&mode=drive&apiKey=%s",
                ROUTING_URL, userLat, userLon, stationLat, stationLon, GEOAPIFY_API_KEY);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            return EntityUtils.toString(httpClient.execute(request).getEntity());
        }
    }
}
