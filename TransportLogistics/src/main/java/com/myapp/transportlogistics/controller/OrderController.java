package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.dto.response.OrderWithRelationsDto;
import com.myapp.transportlogistics.model.Order;
import com.myapp.transportlogistics.service.impl.OrderServiceImpl;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping("get/all")
    public List<OrderResponseDto> getAllOrders() {
        return orderServiceImpl.getAllOrders();
    }

    @GetMapping("get/all-with_relations")
    public List<OrderWithRelationsDto> getAllOrdersWithRelations() {
        return orderServiceImpl.getAllWithRelations();
    }

    @GetMapping("get/{id}")
    public OrderResponseDto getOrderById(@PathVariable Long id) {
        return orderServiceImpl.getOrderById(id);
    }

    @GetMapping("get/by-client/{clientId}")
    public List<OrderResponseDto> getOrderByClientId(@PathVariable Long clientId) {
        return orderServiceImpl.getOrderByClientId(clientId);
    }

    @GetMapping("get/by-driver/{driverId}")
    public List<OrderResponseDto> getOrderByDriverId(@PathVariable Long driverId) {
        return orderServiceImpl.getOrderByDriverId(driverId);
    }

    @PostMapping("post")
    public Order createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderServiceImpl.createOrder(orderRequestDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderServiceImpl.delete(id);
    }
}

