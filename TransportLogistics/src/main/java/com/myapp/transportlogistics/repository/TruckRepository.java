package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Long> {
}
