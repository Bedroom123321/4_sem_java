package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.models.Driver;
import com.myapp.transportlogistics.service.DriverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("drivers")
    public List<Driver> findAlldrivers() {
        return driverService.findAlldrivers();
    }
}
