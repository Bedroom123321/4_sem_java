package com.myapp.transportlogistics.dto.response;

import java.util.Date;
import lombok.Data;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getLoadingPoint() {
        return loadingPoint;
    }

    public void setLoadingPoint(String loadingPoint) {
        this.loadingPoint = loadingPoint;
    }

    public String getDeliveryPoint() {
        return deliveryPoint;
    }

    public void setDeliveryPoint(String deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getTruckId() {
        return truckId;
    }

    public void setTruckId(Long truckId) {
        this.truckId = truckId;
    }
}
