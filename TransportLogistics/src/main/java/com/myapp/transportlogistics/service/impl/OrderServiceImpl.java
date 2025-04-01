package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.dto.response.OrderWithRelationsDto;
import com.myapp.transportlogistics.mapper.OrderMapper;
import com.myapp.transportlogistics.model.Client;
import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.model.Order;
import com.myapp.transportlogistics.model.Truck;
import com.myapp.transportlogistics.repository.ClientRepository;
import com.myapp.transportlogistics.repository.DriverRepository;
import com.myapp.transportlogistics.repository.OrderRepository;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.OrderService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository,
                            DriverRepository driverRepository, TruckRepository truckRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toDtoList(orders);
    }

    @Override
    @Transactional
    public OrderResponseDto getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new IllegalStateException();
        }

        return orderMapper.toDto(optionalOrder.get());
    }

    @Override
    @Transactional
    public Order createOrder(OrderRequestDto orderRequestDto) {

        Optional<Client> optionalClient = clientRepository.findById(orderRequestDto.getClientId());
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException();
        }

        Optional<Driver> optionalDriver = driverRepository.findById(orderRequestDto.getDriverId());
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException();
        }

        Optional<Truck> optionalTruck = truckRepository.findById(orderRequestDto.getTruckId());
        if (optionalTruck.isEmpty()) {
            throw new IllegalStateException();
        }

        Order order = orderMapper.toEntity(orderRequestDto);
        order.setClient(optionalClient.get());
        order.setDriver(optionalDriver.get());
        order.setTruck(optionalTruck.get());

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new IllegalStateException("Не найден");
        }
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<OrderWithRelationsDto> getAllWithRelations() {
        List<Order> orders = orderRepository.findAllWithRelations();
        return orderMapper.toDtoWithRelationsList(orders);
    }

    @Transactional
    @Override
    public List<OrderResponseDto> getOrderByDriverId(Long driverId) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException();
        }

        List<Order> orders = orderRepository.getOrderByDriverId(driverId);
        return orderMapper.toDtoList(orders);
    }

    @Override
    @Transactional
    public List<OrderResponseDto> getOrderByClientPhoneNumber(String phoneNumber) {
        Optional<Client> optionalClient = clientRepository.findByPhoneNumber(phoneNumber);
        if (optionalClient.isEmpty()) {
            throw new IllegalStateException();
        }

        List<Order> orders = orderRepository.getOrderByClientPhoneNumber(phoneNumber);
        return orderMapper.toDtoList(orders);
    }
}

