package com.myapp.transportlogistics.dto.response;

import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderResponseDto {
    private Long id;
    private Date orderDate;
    private String loadingPoint;
    private String deliveryPoint;
    private Long clientId;
    private Long driverId;
    private Long truckId;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Long id, Date orderDate, String loadingPoint,
                            String deliveryPoint, Long clientId, Long driverId, Long truckId) {
        this.id = id;
        this.orderDate = orderDate;
        this.loadingPoint = loadingPoint;
        this.deliveryPoint = deliveryPoint;
        this.clientId = clientId;
        this.driverId = driverId;
        this.truckId = truckId;
    }
}
