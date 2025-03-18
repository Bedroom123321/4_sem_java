package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.DriverRequestDto;
import com.myapp.transportlogistics.dto.DriverResponseDto;
import com.myapp.transportlogistics.model.Driver;
import java.util.List;

public interface DriverService {
    DriverResponseDto findById(Long id);

    List<DriverResponseDto> findAllDrivers();

    DriverResponseDto create(DriverRequestDto driverRequestDto);

    void delete(Long id);

    void update(Long id, String name, String secondName, String phoneNumber);
}
