package com.myapp.transportlogistics.repository;

import com.myapp.transportlogistics.model.Order;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.truck "
            + "LEFT JOIN FETCH o.client LEFT JOIN FETCH o.driver")
    List<Order> findAllWithRelations();

    List<Order> getOrderByClientId(Long clientId);
}
