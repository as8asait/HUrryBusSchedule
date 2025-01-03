package com.gradProj.HUrry.Services;
import com.gradProj.HUrry.Dto.BusDto;
import com.gradProj.HUrry.Repositories.BusRepository;
import com.gradProj.HUrry.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BusService {
    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public BusDto createBus(BusDto busDto) {

        Bus bus = new Bus();
        bus.setBusNumber(busDto.getBusNumber());
        bus.setRoute(busDto.getRoute());
        bus.setStatus(busDto.getStatus());
        busRepository.save(bus);

        return busDto;
    }

    public Page<Bus> getAllBuses(Pageable pageable) {
        return busRepository.findAll(pageable);
    }

//    public BusDto getBus(Long id) {
//
//    }
}
