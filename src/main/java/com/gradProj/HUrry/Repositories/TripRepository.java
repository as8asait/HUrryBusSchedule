package com.gradProj.HUrry.Repositories;

import com.gradProj.HUrry.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository <Trip, Long> {
    Optional<Trip> findTripByRoute(String route);
    List<Trip> getTripsByRoute(String route);

    Optional<Trip> findTripById(Long id);


    //List<Trip> getTripsByRoute();
}
