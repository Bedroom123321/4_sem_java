package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import jakarta.transaction.Transactional;
import java.util.List;

public interface DriverService {
    DriverResponseDto findById(Long id);

    List<DriverResponseDto> findAllDrivers();

    DriverResponseDto create(DriverRequestDto driverRequestDto);

    List<DriverResponseDto>  addDrivers(List<DriverRequestDto> driverRequestDtos);

    void delete(Long id);

    void update(Long id, String secondName, String phoneNumber);

    @Transactional
    List<DriverResponseDto> getDriversByTruckId(Long truckId);
}
