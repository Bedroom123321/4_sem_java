package com.myapp.transportlogistics.dto.response;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Date orderDate;
    private String loadingPoint;
    private String deliveryPoint;
    private Long clientId;
    private Long driverId;
    private Long truckId;
}
