package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.dto.response.OrderWithRelationsDto;
import jakarta.transaction.Transactional;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderById(Long id);

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    void delete(Long id);

    List<OrderWithRelationsDto> getAllWithRelations();

    List<OrderResponseDto> getOrderByDriver(String firstName, String lastName);

    List<OrderResponseDto> getOrderByClientPhoneNumber(String phoneNumber);

    @Transactional
    void setDriverToNull(Long driverId);

    @Transactional
    void setTruckToNull(Long truckId);

    OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto);
}
