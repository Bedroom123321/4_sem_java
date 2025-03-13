package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Driver;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriversRepository extends JpaRepository<Driver, Long> {
    @Query(value = "select * from drivers where phonenumber = :phoneNumber", nativeQuery = true)
    Optional<Driver> findByPhoneNumber(String phoneNumber);
}
