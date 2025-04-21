package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

public class OrderRequestDto {

    private Date orderDate;

    @NotBlank(message = "Место загрузки обязательно")
    private String loadingPoint;

    @NotBlank(message = "Место доставки обязательно")
    private String deliveryPoint;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    private Long clientId;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    private Long driverId;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    private Long truckId;

    public OrderRequestDto() {

    }

    public OrderRequestDto(Date orderDate, String loadingPoint, String deliveryPoint,
                           Long clientId, Long driverId, Long truckId) {
        this.orderDate = orderDate;
        this.loadingPoint = loadingPoint;
        this.deliveryPoint = deliveryPoint;
        this.clientId = clientId;
        this.driverId = driverId;
        this.truckId = truckId;
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
