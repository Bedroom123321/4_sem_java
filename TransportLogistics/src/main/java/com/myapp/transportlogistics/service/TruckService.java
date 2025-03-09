package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.models.Truck;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TruckService {
    private final ArrayList<Truck> trucks = new ArrayList<>();

    public TruckService() {
        trucks.add(new Truck(1, 15000, 35));
        trucks.add(new Truck(2, 4000, 15));
        trucks.add(new Truck(3, 9000, 26));
    }

    public List<Truck> getTrucks(int liftingCapacity, int cargoVolume) {
        ArrayList<Truck> filteredTrucks = new ArrayList<>();

        for (Truck truck : trucks) {
            if (truck.getCargoVolume() == cargoVolume
                    && truck.getLiftingCapacity() == liftingCapacity) {
                filteredTrucks.add(truck);
            }
        }
        if (filteredTrucks.isEmpty()) {
            throw new IllegalArgumentException("No such trucks found");
        } else {
            return filteredTrucks;
        }
    }

    public Truck getByCargo(int id) {
        for (Truck truck : trucks) {
            if (truck.getId() == id) {
                return truck;
            }
        }
        throw new IllegalArgumentException("Truck with ID " + id + " not found.");
    }
}
