package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.model.Truck;
import java.util.List;

public interface TruckService {
    List<Truck> findAllTrucks();

    Truck create(Truck truck);

    void delete(Long id);

    void update(Long id, String cargoType, int cargoVolume);
}
