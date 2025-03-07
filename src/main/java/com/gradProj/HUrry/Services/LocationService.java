package com.gradProj.HUrry.Services;

import com.gradProj.HUrry.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gradProj.HUrry.Repositories.LocationRepo;

@Service
public class LocationService {
    @Autowired
    private LocationRepo locationRepository;

    public void saveLocation(double latitude, double longitude) {
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        locationRepository.save(location);
    }
}
