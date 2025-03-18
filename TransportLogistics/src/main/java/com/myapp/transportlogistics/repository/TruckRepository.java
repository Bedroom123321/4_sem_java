package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Truck;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

    Optional<Truck> findByNumberPlate(String numberPlate);

}
