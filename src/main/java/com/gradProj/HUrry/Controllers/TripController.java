package com.gradProj.HUrry.Controllers;

import com.gradProj.HUrry.Services.TripService;
import com.gradProj.HUrry.entity.Bus;
import com.gradProj.HUrry.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("{route}")
    public List<Trip> getTripByRoute(@PathVariable String route) {
        return tripService.getTrips();
    }

    @PostMapping
    public void addNewTrip(@RequestBody Trip trip) {
        tripService.addTrip(trip);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Trip>> getAllTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(tripService.getAllTrips(pageable));
    }

}
