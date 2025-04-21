package com.myapp.transportlogistics.dto.response;

import java.util.Date;

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

    public ClientResponseDto getClient() {
        return client;
    }

    public void setClient(ClientResponseDto client) {
        this.client = client;
    }

    public DriverResponseDto getDriver() {
        return driver;
    }

    public void setDriver(DriverResponseDto driver) {
        this.driver = driver;
    }

    public TruckResponseDto getTruck() {
        return truck;
    }

    public void setTruck(TruckResponseDto truck) {
        this.truck = truck;
    }
}
