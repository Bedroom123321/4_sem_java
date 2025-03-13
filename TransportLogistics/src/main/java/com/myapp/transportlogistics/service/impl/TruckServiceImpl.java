package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.model.Truck;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.TruckService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TruckServiceImpl implements TruckService {
    private final TruckRepository truckRepository;

    public TruckServiceImpl(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public List<Truck> findAllTrucks() {
        return truckRepository.findAll();
    }

    @Override
    public Truck create(Truck truck) {
        Optional<Truck> optionalTruck = truckRepository.findByNumberPlate(truck.getNumberPlate());
        if (optionalTruck.isPresent()) {
            throw new IllegalStateException("Транспорт с таким номером уже существует");
        }
        return truckRepository.save(truck);
    }

    @Override
    public void delete(Long id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if (optionalTruck.isEmpty()) {
            throw new IllegalStateException("Транспорта с id " + id + " нет в базе");
        }
        truckRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String cargoType, int cargoVolume) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if (optionalTruck.isEmpty()) {
            throw new IllegalStateException("Транспорта с id " + id + " нет в базе");
        }

        Truck truck = optionalTruck.get();

        if (cargoType != null && !cargoType.equals(truck.getCargoType())) {
            truck.setCargoType(cargoType);
        }

        if (cargoVolume != 0 && cargoVolume != truck.getCargoVolume()) {
            truck.setCargoVolume(cargoVolume);
        }

        truckRepository.save(truck);
    }
}
