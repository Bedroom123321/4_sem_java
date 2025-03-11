package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.models.Truck;
import com.myapp.transportlogistics.service.TruckService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping
    public List<Truck> getTrucks() {
        return truckService.findAll();
    }
}
