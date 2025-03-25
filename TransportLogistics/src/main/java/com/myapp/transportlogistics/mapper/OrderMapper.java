package com.myapp.transportlogistics.mapper;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.model.Client;
import com.myapp.transportlogistics.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
            dto.setDriverId(order.getDriver().getId());
            dto.setClientId((order.getClient().getId()));
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
