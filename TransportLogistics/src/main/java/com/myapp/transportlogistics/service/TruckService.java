package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import java.util.List;

public interface TruckService {
    TruckResponseDto findById(Long id);

    List<TruckResponseDto> findAllTrucks();

    TruckResponseDto addTruck(TruckRequestDto truck);

    List<TruckResponseDto> addTrucks(List<TruckRequestDto> truckRequestDtos);

    void delete(Long id);

    TruckResponseDto update(Long id, TruckRequestDto truckRequestDto);

    List<TruckResponseDto> getTrucksByDriverId(Long driverId);
}
