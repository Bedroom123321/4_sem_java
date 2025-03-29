package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import com.myapp.transportlogistics.service.impl.TruckServiceImpl;
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

    private final TruckServiceImpl truckServiceImpl;

    public TruckController(TruckServiceImpl truckServiceImpl) {
        this.truckServiceImpl = truckServiceImpl;
    }

    @GetMapping("get/all")
    public List<TruckResponseDto> getTrucks() {
        return truckServiceImpl.findAllTrucks();
    }

    @GetMapping("get/{id}")
    public TruckResponseDto getTruckById(@PathVariable Long id) {
        return truckServiceImpl.findById(id);
    }

    @GetMapping("get/by-driver/{driverId}")
    public List<TruckResponseDto> getTrucksByDriverId(@PathVariable Long driverId) {
        return truckServiceImpl.getTrucksByDriverId(driverId);
    }

    @PostMapping("post")
    public TruckResponseDto creatTruck(@RequestBody TruckRequestDto truckRequestDto) {
        return truckServiceImpl.create(truckRequestDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTruck(@PathVariable Long id) {
        truckServiceImpl.delete(id);
    }

    @PutMapping("update/{id}")
    public void updateTruck(@PathVariable Long id, @RequestParam(required = false) String cargoType,
                            @RequestParam(required = false) Integer cargoVolume) {
        truckServiceImpl.update(id, cargoType, cargoVolume);
    }
}
