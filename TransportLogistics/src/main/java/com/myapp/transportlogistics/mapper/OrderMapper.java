package com.myapp.transportlogistics.mapper;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.model.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

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

}
