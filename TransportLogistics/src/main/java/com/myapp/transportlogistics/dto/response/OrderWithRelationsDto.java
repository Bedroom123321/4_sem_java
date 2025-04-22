package com.myapp.transportlogistics.dto.response;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderWithRelationsDto {
    private Long id;
    private Date orderDate;
    private String loadingPoint;
    private String deliveryPoint;
    private ClientResponseDto client;
    private DriverResponseDto driver;
    private TruckResponseDto truck;

    public OrderWithRelationsDto() {

    }

    public OrderWithRelationsDto(Long id, Date orderDate, String loadingPoint,
                                 String deliveryPoint, ClientResponseDto client,
                                 DriverResponseDto driver, TruckResponseDto truck) {
        this.id = id;
        this.orderDate = orderDate;
        this.loadingPoint = loadingPoint;
        this.deliveryPoint = deliveryPoint;
        this.client = client;
        this.driver = driver;
        this.truck = truck;
    }
}
