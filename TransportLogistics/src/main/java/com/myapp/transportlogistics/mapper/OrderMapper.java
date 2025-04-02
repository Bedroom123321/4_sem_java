package com.myapp.transportlogistics.mapper;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.dto.response.OrderWithRelationsDto;
import com.myapp.transportlogistics.model.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ClientMapper clientMapper;
    private final DriverMapper driverMapper;
    private final TruckMapper truckMapper;

    public OrderMapper(ClientMapper clientMapper,
                       DriverMapper driverMapper, TruckMapper truckMapper) {
        this.clientMapper = clientMapper;
        this.driverMapper = driverMapper;
        this.truckMapper = truckMapper;
    }

    public Order toEntity(OrderRequestDto dto) {
        Order order = new Order();
        order.setOrderDate(dto.getOrderDate());
        order.setLoadingPoint(dto.getLoadingPoint());
        order.setDeliveryPoint(dto.getDeliveryPoint());
        return order;
    }

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setLoadingPoint(order.getLoadingPoint());
        dto.setDeliveryPoint(order.getDeliveryPoint());
        dto.setClientId((order.getClient().getId()));
        dto.setDriverId(order.getDriver().getId());
        dto.setTruckId(order.getTruck().getId());
        return dto;
    }

    public List<OrderResponseDto> toDtoList(List<Order> orders) {
        ArrayList<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        for (Order order : orders) {
            orderResponseDtos.add(toDto(order));
        }
        return orderResponseDtos;
    }

    public OrderWithRelationsDto toDtoWithRelations(Order order) {
        OrderWithRelationsDto dto = new OrderWithRelationsDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setLoadingPoint(order.getLoadingPoint());
        dto.setDeliveryPoint(order.getDeliveryPoint());

        if (order.getClient() != null) {
            dto.setClient(clientMapper.toDto(order.getClient()));
        }

        if (order.getDriver() != null) {
            dto.setDriver(driverMapper.toDto(order.getDriver()));
        }

        if (order.getTruck() != null) {
            dto.setTruck(truckMapper.toDto(order.getTruck()));
        }

        return dto;
    }

    public List<OrderWithRelationsDto> toDtoWithRelationsList(List<Order> orders) {
        ArrayList<OrderWithRelationsDto> orderWithRelationsDtos = new ArrayList<>();
        for (Order order : orders) {
            orderWithRelationsDtos.add(toDtoWithRelations(order));
        }
        return orderWithRelationsDtos;
    }

}
