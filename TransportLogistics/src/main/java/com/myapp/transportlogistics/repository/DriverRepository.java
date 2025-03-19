package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Driver;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByPhoneNumber(String phoneNumber);
}
