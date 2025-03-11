package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.models.Truck;
import com.myapp.transportlogistics.repository.TruckRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TruckService {
    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public List<Truck> findAll() {
        return truckRepository.findAll();
    }

}
