package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Truck;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

    Optional<Truck> findByNumberPlate(String numberPlate);

    @Query("SELECT DISTINCT t FROM Truck t "
            + "JOIN Order o ON t.id = o.truck.id "
            + "WHERE o.driver.id = :driverId")
    List<Truck> getTrucksByDriverId(Long driverId);
}
