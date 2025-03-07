package com.gradProj.HUrry.Controllers;

import com.gradProj.HUrry.Dto.LocationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @PostMapping
    public ResponseEntity<String> receiveLocation(@RequestBody LocationRequestDto locationRequest) {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();

        // Process the coordinates (e.g., save to database, perform calculations, etc.)
        System.out.println("Received coordinates - Latitude: " + latitude + ", Longitude: " + longitude);

        return ResponseEntity.ok("Coordinates received successfully");
    }
}