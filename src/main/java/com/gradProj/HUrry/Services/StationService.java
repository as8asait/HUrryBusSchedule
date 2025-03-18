package com.gradProj.HUrry.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gradProj.HUrry.Dto.StationDto;
import com.gradProj.HUrry.Repositories.StationRepository;
import com.gradProj.HUrry.entity.Station;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StationService {
    private final StationRepository stationRepo;

    @Autowired
    private RestTemplate restTemplate;

    private static final String GEOAPIFY_API_KEY = "c94061a37e1b48bb902f8ca597534b62";
    private static final String GEOCODING_API_URL = "https://api.geoapify.com/v1/geocode/search";
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

    public List<StationDto> findClosestStations(double userLat, double userLon) {
        List<StationDto> closestStations = new ArrayList<>();
        List<StationDto> stationDtoList = new ArrayList<>();

        List<Station> allStations = stationRepo.findAll();

        for (Station station : allStations) {
            StationDto stationDto = transferToStationDto(station);
            fillStationData(userLat, userLon, station.getLat(), station.getLon(),stationDto);
            stationDtoList.add(stationDto);
            if(stationDto.getDistance()<=15) {
                closestStations.add(stationDto);
            }
        }
        if (closestStations.isEmpty()) {
            return stationDtoList.stream().sorted(Comparator.comparingDouble(StationDto::getDistance)).toList();
        }
        return closestStations.stream().sorted(Comparator.comparingDouble(StationDto::getDistance)).toList();
    }

    public JsonNode forwardGeocode(String address) throws Exception {
        String url = String.format("%s?text=%s&apiKey=%s", GEOCODING_API_URL, address, GEOAPIFY_API_KEY);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            String response = EntityUtils.toString(httpClient.execute(request).getEntity());
            return new ObjectMapper().readTree(response);
        }
    }

//    public String getRoute(double userLat, double userLon, double stationLat, double stationLon) throws Exception {
//        String url = String.format("%s?waypoints=%f,%f|%f,%f&mode=drive&apiKey=%s",
//                ROUTING_URL, userLat, userLon, stationLat, stationLon, GEOAPIFY_API_KEY);
//
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            HttpGet request = new HttpGet(url);
//            String response = EntityUtils.toString(httpClient.execute(request).getEntity());
//            JsonNode jsonNode = new ObjectMapper().readTree(response);
//            if (jsonNode.path("features").isEmpty()) {
//
//                throw new Exception("No address found for the given coordinates.");
//            }
//            String distance = jsonNode.path("features").get(0).path("properties").path("distance").asText();
//            String time = jsonNode.path("features").get(0).path("properties").path("time").asText();
//
//        }
//        return null;
//    }

    public void fillStationData (double userLat, double userLon, double stationLat, double stationLon, StationDto stationDto){
        String url =  String.format("%s?waypoints=%.14f,%.14f%%7C%.14f,%.14f&mode=drive&apiKey=%s",
                ROUTING_URL, userLat, userLon, stationLat, stationLon, GEOAPIFY_API_KEY);
//        String url = "https://api.geoapify.com/v1/routing?waypoints=" + userLat + "," + userLon + "" + stationLat + "," + stationLon + "&mode=drive&apiKey=" + GEOAPIFY_API_KEY;
//        try {
//            ResponseEntity<String> response = restTemplate.getForEntity(new URI(url), String.class);
//            JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
//
//            if (jsonNode.path("features").isEmpty()) {
//                throw new Exception("No address found for the given coordinates.");
//            }
//
//            stationDto.setDistance(Double.parseDouble(jsonNode.path("features").get(0).path("properties").path("distance").asText()));
//            stationDto.setTime(Double.parseDouble(jsonNode.path("features").get(0).path("properties").path("time").asText()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(request);
            String response = EntityUtils.toString(httpResponse.getEntity());
            JsonNode jsonNode = new ObjectMapper().readTree(response);
            if (jsonNode.path("features").isEmpty()) {
                throw new Exception("No address found for the given coordinates.");
            }
            double distanceInKm=Double.parseDouble(jsonNode.path("features").get(0).path("properties").path("distance").asText())/1000;
            stationDto.setDistance(distanceInKm);
            double timeInMin=Double.parseDouble(jsonNode.path("features").get(0).path("properties").path("time").asText())/60;
            stationDto.setTime(timeInMin);

        } catch (Exception e){
            log.error(e.getMessage());
            log.error("Error while calling geoapify");
        }
    }
}
