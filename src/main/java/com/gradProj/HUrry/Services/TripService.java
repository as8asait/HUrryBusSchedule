package com.gradProj.HUrry.Services;

import com.gradProj.HUrry.Repositories.TripRepository;
import com.gradProj.HUrry.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Page<Trip> getAllTrips(Pageable pageable) {
        return tripRepository.findAll(pageable);
    }

    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }
    public List<Trip> getTripsByRouteId(String route){
        return tripRepository.getTripsByRoute(route);
    }
    public void addTrip(Trip trip){
        Optional<Trip> tripOptional = tripRepository.findTripById(trip.getId());
        if (tripOptional.isPresent()){
            throw new IllegalArgumentException("Trip already exists!");
        }
        tripRepository.save(trip);
    }
}
