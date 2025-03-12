package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriversRepository extends JpaRepository<Driver, Long> {
    @Query(value = "select * from drivers where phonenumber = :phoneNumber", nativeQuery = true)
    Optional<Driver> findByPhoneNumber(String phoneNumber);
}
