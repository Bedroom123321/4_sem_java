package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TruckRepository extends JpaRepository<Truck, Long> {

    @Query(value = "select * from trucks where numberplate = :numberPlate", nativeQuery = true)
    Optional<Truck> findByNumberPlate (String numberPlate);
}
