package com.myapp.transportlogistics.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponseDto {
        private Long id;
        private Date orderDate;
        private String loadingPoint;
        private String deliveryPoint;
        private Long clientId;
        private Long driverId;
}
