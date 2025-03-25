package com.myapp.transportlogistics.dto.request;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Date orderDate;
    private String loadingPoint;
    private String deliveryPoint;
    private Long clientId;
    private Long driverId;
    private Long truckId;
}
