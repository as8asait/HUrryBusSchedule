package com.gradProj.HUrry.Controllers;

import com.gradProj.HUrry.Dto.BusDto;
import com.gradProj.HUrry.Services.BusService;
import com.gradProj.HUrry.entity.Bus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/buses")
public class BusController {
    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping("/add")
    public ResponseEntity<BusDto> addBus(@RequestBody BusDto busDto) {
        return ResponseEntity.ok(busService.createBus(busDto));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Bus>> getAllBuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(busService.getAllBuses(pageable));
    }
}
