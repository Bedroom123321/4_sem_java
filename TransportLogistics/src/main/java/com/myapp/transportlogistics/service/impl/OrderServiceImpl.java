package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.dto.response.OrderWithRelationsDto;
import com.myapp.transportlogistics.exception.EntityNotFoundException;
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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;
    private final OrderMapper orderMapper;

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
            throw new EntityNotFoundException("Заказ с таким ID не найден");
        }

        return orderMapper.toDto(optionalOrder.get());
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Optional<Client> optionalClient = clientRepository.findById(orderRequestDto.getClientId());
        if (optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Клиент с таким ID не найден");
        }

        Optional<Driver> optionalDriver = driverRepository.findById(orderRequestDto.getDriverId());
        if (optionalDriver.isEmpty()) {
            throw new EntityNotFoundException("Водитель с таким ID не найден");
        }

        Optional<Truck> optionalTruck = truckRepository.findById(orderRequestDto.getTruckId());
        if (optionalTruck.isEmpty()) {
            throw new EntityNotFoundException("Транспорт с таким ID не найден");
        }

        Order order = orderMapper.toEntity(orderRequestDto);
        order.setClient(optionalClient.get());
        order.setDriver(optionalDriver.get());
        order.setTruck(optionalTruck.get());

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new EntityNotFoundException("Заказ с таким ID не найден");
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
    public List<OrderResponseDto> getOrderByDriver(String firstName, String lastName) {
        Optional<Driver> optionalDriver = driverRepository.findByLastName(lastName);
        if (optionalDriver.isEmpty()) {
            throw new EntityNotFoundException("Водитель с таким ID не найден");
        }

        List<Order> orders = orderRepository.getOrderByDriver(firstName, lastName);
        return orderMapper.toDtoList(orders);
    }

    @Override
    @Transactional
    public List<OrderResponseDto> getOrderByClientPhoneNumber(String phoneNumber) {
        Optional<Client> optionalClient = clientRepository.findByPhoneNumber(phoneNumber);
        if (optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Клиент с таким ID не найден");
        }

        List<Order> orders = orderRepository.getOrderByClientPhoneNumber(phoneNumber);
        return orderMapper.toDtoList(orders);
    }

    @Override
    @Transactional
    public void setDriverToNull(Long driverId) {
        List<Order> orders = orderRepository.findByDriverId(driverId);
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                order.setDriver(null);
                orderRepository.save(order);
            }
        }
    }

    @Override
    @Transactional
    public void setTruckToNull(Long truckId) {
        List<Order> orders = orderRepository.findByTruckId(truckId);
        if (orders.isEmpty()) {
            for (Order order : orders) {
                order.setTruck(null);
                orderRepository.save(order);
            }
        }
    }

}

