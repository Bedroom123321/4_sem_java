package com.myapp.transportlogistics.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderRequestDto {
    private Date orderDate;
    private String loadingPoint;
    private String deliveryPoint;
    private Long clientId;
    private Long driverId;
}
