package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.model.Order;
import com.myapp.transportlogistics.service.impl.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders")
public class OrderController {

        private final OrderServiceImpl orderServiceImpl;

        public OrderController(OrderServiceImpl orderServiceImpl) {
            this.orderServiceImpl = orderServiceImpl;
        }

        @GetMapping
        public List<OrderResponseDto> getAllOrders() {
            return orderServiceImpl.getAllOrders();
        }

        @GetMapping("/{id}")
        public OrderResponseDto getOrderById(@PathVariable Long id) {
            return orderServiceImpl.getOrderById(id);
        }

        @PostMapping("post")
        public Order createOrder(@RequestBody OrderRequestDto orderRequestDto) {
            return orderServiceImpl.createOrder(orderRequestDto);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
            try {
                Order order = orderServiceImpl.updateOrder(id, updatedOrder);
                return ResponseEntity.ok(order);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }
}

