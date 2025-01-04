package com.gradProj.HUrry.Services;

import com.gradProj.HUrry.Dto.StationDto;
import com.gradProj.HUrry.Repositories.StationRepository;
import com.gradProj.HUrry.entity.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
    public StationDto createStation(StationDto stationDto) {

        Station station = new Station();
        station.setStation_name(stationDto.getStation_name());
        stationRepository.save(station);

        return stationDto;
    }

    public List<Station> getAllStations() {
       return stationRepository.findAll();
    }
}
