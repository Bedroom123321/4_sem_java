package com.myapp.transportlogistics.dto.response;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderWithRelationsDto {
    private Long id;
    private Date orderDate;
    private String loadingPoint;
    private String deliveryPoint;
    private ClientResponseDto client;
    private DriverResponseDto driver;
    private TruckResponseDto truck;
}
