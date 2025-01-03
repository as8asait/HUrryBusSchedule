package com.gradProj.HUrry.config;

import com.gradProj.HUrry.Repositories.TripRepository;
import com.gradProj.HUrry.entity.Bus;
import com.gradProj.HUrry.entity.Trip;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TripConfig {
    @Bean
    CommandLineRunner commandLineRunner(TripRepository tripRepository) {
        return args -> {
            Trip trip = new Trip();
        };
    }
}
