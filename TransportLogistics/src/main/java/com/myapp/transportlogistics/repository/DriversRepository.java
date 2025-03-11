package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.models.Driver;
import com.myapp.transportlogistics.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriversRepository extends JpaRepository<Driver, Long> {
}
