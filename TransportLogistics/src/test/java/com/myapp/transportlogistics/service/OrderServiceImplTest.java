package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.exception.EntityAlreadyExistsException;
import com.myapp.transportlogistics.exception.EntityNotFoundException;
import com.myapp.transportlogistics.mapper.OrderMapper;
import com.myapp.transportlogistics.model.Client;
import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.model.Order;
import com.myapp.transportlogistics.repository.ClientRepository;
import com.myapp.transportlogistics.repository.DriverRepository;
import com.myapp.transportlogistics.repository.OrderRepository;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private TruckRepository truckRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private Order order1;
    @Mock
    private Order order2;
    private OrderRequestDto orderRequestDto1 ;
    private OrderRequestDto orderRequestDto2;
    private OrderResponseDto orderResponseDto1;
    private OrderResponseDto orderResponseDto2;
    private final Long firstOrderId = 1L;
    private final String clientPhoneNumber = "+375293332211";
    private final String driverFirstName = "Илья";
    private final String driverLastName = "Ходин";

    @BeforeEach
    void setUp() {
        /*order1 = new Order();*/
        orderRequestDto1 = new OrderRequestDto();
        orderRequestDto2 = new OrderRequestDto();
        /*order2 = new Order();*/
        orderResponseDto1 = new OrderResponseDto();
        orderResponseDto2 = new OrderResponseDto();
    }

    @Test
    void getAllOrders() {
        Mockito.when(orderRepository.findAll()).thenReturn(List.of(order1, order2));
        Mockito.when(orderMapper.toDtoList(List.of(order1, order2)))
                .thenReturn(List.of(orderResponseDto1,orderResponseDto2));

        List<OrderResponseDto> result = orderServiceImpl.getAllOrders();

        Assertions.assertEquals(List.of(orderResponseDto1,orderResponseDto2), result);
    }

    @Test
    void getOrderById() {
        Mockito.when(orderRepository.findById(firstOrderId)).thenReturn(Optional.of(order1));
        Mockito.when(orderMapper.toDto(order1)).thenReturn(orderResponseDto1);

        OrderResponseDto result = orderServiceImpl.getOrderById(firstOrderId);

        Assertions.assertEquals(orderResponseDto1, result);
    }

    @Test
    void getOrderById_notFoundException() {

        Mockito.when(orderRepository.findById(firstOrderId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> orderServiceImpl.getOrderById(firstOrderId));
    }

    @Test
    void createOrder() {
    }

    @Test
    void delete() {
        Mockito.when(orderRepository.findById(firstOrderId)).thenReturn(Optional.of(order1));

        orderServiceImpl.delete(firstOrderId);

        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(firstOrderId);
    }

    @Test
    void testDelete_notFoundException() {
        Mockito.when(orderRepository.findById(firstOrderId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> orderServiceImpl.delete(firstOrderId));
    }

    @Test
    void getAllWithRelations() {

    }

    @Test
    void getOrderByDriver() {
        Driver driver = new Driver();

        Mockito.when(driverRepository.findByFirstNameAndLastName(driverFirstName, driverLastName)).thenReturn(Optional.of(driver));
        Mockito.when(orderRepository.getOrderByDriver(driverFirstName, driverLastName)).thenReturn(List.of(order1, order2));
        Mockito.when(orderMapper.toDtoList(List.of(order1, order2))).thenReturn(List.of(orderResponseDto1,orderResponseDto2));

        List<OrderResponseDto> result = orderServiceImpl.getOrderByDriver(driverFirstName, driverLastName);

        Assertions.assertEquals(List.of(orderResponseDto1,orderResponseDto2), result);

    }

    @Test
    void getOrderByDriver_notFoundException() {
        Mockito.when(driverRepository.findByFirstNameAndLastName(driverFirstName, driverLastName)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> orderServiceImpl
                .getOrderByDriver(driverFirstName, driverLastName));
    }

    @Test
    void getOrderByClientPhoneNumber() {
        Client client = new Client();

        Mockito.when(clientRepository.findByPhoneNumber(clientPhoneNumber)).thenReturn(Optional.of(client));
        Mockito.when(orderRepository.getOrderByClientPhoneNumber(clientPhoneNumber)).thenReturn(List.of(order1, order2));
        Mockito.when(orderMapper.toDtoList(List.of(order1, order2))).thenReturn(List.of(orderResponseDto1,orderResponseDto2));

        List<OrderResponseDto> result = orderServiceImpl.getOrderByClientPhoneNumber(clientPhoneNumber);

        Assertions.assertEquals(List.of(orderResponseDto1,orderResponseDto2), result);
    }

    @Test
    void getOrderByClientPhoneNumber_notFoundException() {
        Mockito.when(clientRepository.findByPhoneNumber(clientPhoneNumber)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> orderServiceImpl
                .getOrderByClientPhoneNumber(clientPhoneNumber));
    }

    @Test
    void setDriverToNull() {
        Long driverId = 1L;

        Mockito.when(orderRepository.findByDriverId(driverId)).thenReturn(List.of(order1,order2));

        orderServiceImpl.setDriverToNull(driverId);

        for (Order order : List.of(order1, order2)) {
            Mockito.verify(order, Mockito.times(1)).setDriver(null);
            Mockito.verify(orderRepository, Mockito.times(1)).save(order);
        }
    }

    @Test
    void setTruckToNull() {
        Long truckId = 1L;

        Mockito.when(orderRepository.findByTruckId(truckId)).thenReturn(List.of(order1,order2));

        orderServiceImpl.setTruckToNull(truckId);

        for (Order order : List.of(order1, order2)) {
            Mockito.verify(order, Mockito.times(1)).setTruck(null);
            Mockito.verify(orderRepository, Mockito.times(1)).save(order);
        }
    }
}