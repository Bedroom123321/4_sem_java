package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.models.Truck;
import com.myapp.transportlogistics.service.TruckService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping
    public List<Truck> getTrucks(@RequestParam int cargoVolume,
                                 @RequestParam int liftingCapacity) {
        return truckService.getTrucks(liftingCapacity, cargoVolume);
    }

    @GetMapping("{id}")
    public Truck getByCargo(@PathVariable int id) {
        return truckService.getByCargo(id);
    }
}
