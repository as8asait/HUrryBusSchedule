package com.gradProj.HUrry.Services;

import com.gradProj.HUrry.Repositories.BusScheduleRepository;
import com.gradProj.HUrry.entity.BusSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusScheduleService {
    @Autowired
    private BusScheduleRepository repository;

    public List<BusSchedule> getAllSchedules() {
        return repository.findAll();
    }

    public BusSchedule createSchedule(BusSchedule schedule) {
        return repository.save(schedule);
    }
}
