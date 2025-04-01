package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Driver;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByPhoneNumber(String phoneNumber);

    @Query("SELECT DISTINCT d FROM Driver d "
            + "JOIN Order o ON d.id = o.driver.id "
            + "WHERE o.truck.id = :truckId")
    List<Driver> getDriversByTruckId(Long truckId);

    Optional<Driver> findBySecondName(String secondName);
}
