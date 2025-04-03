package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Order;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.truck "
            + "LEFT JOIN FETCH o.client LEFT JOIN FETCH o.driver")
    List<Order> findAllWithRelations();

    @Query("SELECT o FROM Order o WHERE o.client.phoneNumber = :phoneNumber")
    List<Order> getOrderByClientPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value = "SELECT o.* FROM orders o JOIN drivers d ON o.driver_id = d.id "
            + "WHERE d.first_name = :firstName AND d.last_name = :lastName", nativeQuery = true)
    List<Order> getOrderByDriver(String firstName, String lastName);

    List<Order> findByDriverId(Long driverId);

    List<Order> findByTruckId(Long truckId);
}


