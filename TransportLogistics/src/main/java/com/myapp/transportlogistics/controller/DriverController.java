package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.service.impl.DriverServiceImpl;
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
@RequestMapping("drivers")
public class DriverController {

    private final DriverServiceImpl driverService;

    public DriverController(DriverServiceImpl driverService) {
        this.driverService = driverService;
    }

    @GetMapping("get")
    public List<Driver> findAlldrivers() {
        return driverService.findAllDrivers();
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
