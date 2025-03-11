package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.models.Truck;
import com.myapp.transportlogistics.repository.DriversRepository;
import com.myapp.transportlogistics.repository.TruckRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TruckService {
    private final TruckRepository truckRepository;


    public TruckService(TruckRepository truckRepository, DriversRepository driversRepository) {
        this.truckRepository = truckRepository;
    }

    public List<Truck> findAlltrucks() {
        return truckRepository.findAll();
    }

    public Truck create(Truck truck) {
        Optional<Truck> optionalTruck = truckRepository.findByNumberPlate(truck.getNumberPlate());
        if (optionalTruck.isPresent()) {
            throw new IllegalStateException("Транспорт с таким номером уже существует");
        }
        return truckRepository.save(truck);
    }

}
