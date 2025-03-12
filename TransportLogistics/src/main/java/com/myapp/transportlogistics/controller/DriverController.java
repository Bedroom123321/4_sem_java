package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.models.Driver;
import com.myapp.transportlogistics.service.DriverService;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("get")
    public List<Driver> findAlldrivers() {
        return driverService.findAlldrivers();
    }

    @PostMapping
    public Driver creatDriver(@RequestBody Driver driver) {
        return driverService.creat(driver);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTruck(@PathVariable Long id) {
        driverService.delete(id);
    }

    @PutMapping("update/{id}")
    public void updateTruck(@PathVariable Long id, @RequestParam(required = false) String name,
                            @RequestParam(required = false) String secondName,
                            @RequestParam(required = false) String phoneNumber) {
        driverService.update(id, name, secondName, phoneNumber);
    }
}
