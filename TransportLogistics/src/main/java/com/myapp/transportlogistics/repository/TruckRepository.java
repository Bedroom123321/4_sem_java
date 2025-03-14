package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Truck;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TruckRepository extends JpaRepository<Truck, Long> {

    @Query(value = "select * from trucks where numberplate = :numberPlate", nativeQuery = true)
    Optional<Truck> findByNumberPlate(String numberPlate);

}
