package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.models.Truck;
import com.myapp.transportlogistics.service.TruckService;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping("trucks")
    public List<Truck> getTrucks() {
        return truckService.findAlltrucks();
    }

    @PostMapping
    public Truck creat(@RequestBody Truck truck) {
        return truckService.create(truck);
    }
}
