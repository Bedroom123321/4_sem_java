package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.models.Driver;
import com.myapp.transportlogistics.repository.DriversRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriversRepository driversRepository;

    public DriverService(DriversRepository driversRepository) {
        this.driversRepository = driversRepository;
    }

    public List<Driver> findAlldrivers() {
        return driversRepository.findAll();
    }
}
