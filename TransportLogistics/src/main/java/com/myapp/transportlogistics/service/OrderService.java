package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.dto.response.OrderWithRelationsDto;
import com.myapp.transportlogistics.model.Order;
import jakarta.transaction.Transactional;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderById(Long id);

    Order createOrder(OrderRequestDto orderRequestDto);

    void delete(Long id);

    List<OrderWithRelationsDto> getAllWithRelations();

    List<OrderResponseDto> getOrderByClientId(Long clientId);

    @Transactional
    List<OrderResponseDto> getOrderByDriverId(Long driverId);
}
