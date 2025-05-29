package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import java.util.List;

public interface DriverService {
    DriverResponseDto findById(Long id);

    List<DriverResponseDto> findAllDrivers();

    DriverResponseDto create(DriverRequestDto driverRequestDto);

    List<DriverResponseDto> addDrivers(List<DriverRequestDto> driverRequestDtos);

    void delete(Long id);

    DriverResponseDto update(Long id, DriverRequestDto driverRequestDto);

    List<DriverResponseDto> getDriversByTruckId(Long truckId);
}
