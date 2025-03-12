package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.models.Truck;
import com.myapp.transportlogistics.service.TruckService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("get")
    public List<Truck> getTrucks() {
        return truckService.findAlltrucks();
    }

    @PostMapping("post")
    public Truck creatTruck(@RequestBody Truck truck) {
        return truckService.create(truck);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTruck(@PathVariable Long id) {
        truckService.delete(id);
    }

    @PutMapping("update/{id}")
    public void updateTruck(@PathVariable Long id, @RequestParam(required = false) String cargoType,
                            @RequestParam(required = false) int cargoVolume) {
        truckService.update(id, cargoType, cargoVolume);
    }
}
