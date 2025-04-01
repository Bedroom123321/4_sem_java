package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.dto.response.OrderWithRelationsDto;
import com.myapp.transportlogistics.model.Order;
import com.myapp.transportlogistics.service.impl.OrderServiceImpl;
import java.util.List;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("get/by-client")
    public List<OrderResponseDto> getOrderByClientPhoneNumber(@RequestParam String phoneNumber) {
        return orderServiceImpl.getOrderByClientPhoneNumber(phoneNumber);
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

