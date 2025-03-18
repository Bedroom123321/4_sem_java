package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.TruckRequestDto;
import com.myapp.transportlogistics.dto.TruckResponseDto;
import java.util.List;

public interface TruckService {
    TruckResponseDto findById(Long id);

    List<TruckResponseDto> findAllTrucks();

    TruckResponseDto create(TruckRequestDto truck);

    void delete(Long id);

    void update(Long id, String cargoType, int cargoVolume);
}
