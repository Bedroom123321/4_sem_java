package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
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

    private final DriverServiceImpl driverServiceImpl;

    public DriverController(DriverServiceImpl driverService) {
        this.driverServiceImpl = driverService;
    }

    @GetMapping("get/all")
    public List<DriverResponseDto> getAllDrivers() {
        return driverServiceImpl.findAllDrivers();
    }

    @GetMapping("get/{id}")
    public DriverResponseDto getDriverById(@PathVariable Long id) {
        return driverServiceImpl.findById(id);
    }

    @GetMapping("get/by-truck/{truckId}")
    public List<DriverResponseDto> getDriversByTruckId(@PathVariable Long truckId) {
        return driverServiceImpl.getDriversByTruckId(truckId);
    }

    @PostMapping("post")
    public DriverResponseDto createDriver(@RequestBody DriverRequestDto driverRequestDto) {
        return driverServiceImpl.create(driverRequestDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverServiceImpl.delete(id);
    }

    @PutMapping("update/{id}")
    public void updateDriver(@PathVariable Long id,
                             @RequestParam(required = false) String secondName,
                             @RequestParam(required = false) String phoneNumber) {
        driverServiceImpl.update(id, secondName, phoneNumber);
    }

}
