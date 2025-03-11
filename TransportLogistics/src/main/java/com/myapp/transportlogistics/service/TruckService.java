package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.models.Truck;
import com.myapp.transportlogistics.repository.DriversRepository;
import com.myapp.transportlogistics.repository.TruckRepository;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
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

    public void delete(Long id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if(optionalTruck.isEmpty())
        {
            throw new IllegalStateException("Транспорта с id " + id + " нет в базе");
        }
        truckRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String cargoType, int cargoVolume) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if(optionalTruck.isEmpty())
        {
            throw new IllegalStateException("Транспорта с id " + id + " нет в базе");
        }
        Truck truck = optionalTruck.get();

        if(cargoType != null && !cargoType.equals(truck.getCargoType())) {
            truck.setCargoType(cargoType);
        }

        if(cargoVolume != 0 && cargoVolume != truck.getCargoVolume()) {
            truck.setCargoVolume(cargoVolume);
        }

        truckRepository.save(truck);
    }
}
