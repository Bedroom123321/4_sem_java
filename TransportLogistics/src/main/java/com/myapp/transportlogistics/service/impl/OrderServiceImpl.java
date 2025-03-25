package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.mapper.OrderMapper;
import com.myapp.transportlogistics.model.Client;
import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.model.Order;
import com.myapp.transportlogistics.model.Truck;
import com.myapp.transportlogistics.repository.ClientRepository;
import com.myapp.transportlogistics.repository.DriverRepository;
import com.myapp.transportlogistics.repository.OrderRepository;
import com.myapp.transportlogistics.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final DriverRepository driverRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository, DriverRepository driverRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders =orderRepository.findAll();
        return orderMapper.toDtoList(orders);
    }

    public OrderResponseDto getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new IllegalStateException("Заказа с id " + id + " нет в базе");
        }

        return orderMapper.toDto(optionalOrder.get());
    }

    public Order createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.toEntity(orderRequestDto);

        Optional<Driver> optionalDriver = driverRepository.findById(orderRequestDto.getDriverId());
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + orderRequestDto.getDriverId() + " нет в базе");
        }

        Optional<Client> optionalClient = clientRepository.findById(orderRequestDto.getClientId());
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException("Клиента с id " + orderRequestDto.getClientId() + " нет в базе");
        }

        order.setClient(optionalClient.get());
        order.setDriver(optionalDriver.get());

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderDate(updatedOrder.getOrderDate());
            order.setLoadingPoint(updatedOrder.getLoadingPoint());
            order.setDeliveryPoint(updatedOrder.getDeliveryPoint());
            order.setClient(updatedOrder.getClient());
            order.setDriver(updatedOrder.getDriver());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}

