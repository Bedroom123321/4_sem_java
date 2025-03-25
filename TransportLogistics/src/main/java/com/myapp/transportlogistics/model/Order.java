package com.myapp.transportlogistics.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date orderDate;
    private String loadingPoint;
    private String deliveryPoint;

    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Order() {
    }

    public Order(Date orderDate, String loadingPoint,
                 String deliveryPoint, Client client) {
        this.orderDate = orderDate;
        this.loadingPoint = loadingPoint;
        this.deliveryPoint = deliveryPoint;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + (orderDate != null ? orderDate : "null") +
                ", loadingPoint='" + loadingPoint + '\'' +
                ", deliveryPoint='" + deliveryPoint + '\'' +
                ", clientId=" + (client != null ? client.getId() : "null") +
                ", driverId=" + (driver != null ? driver.getId() : "null") +
                '}';
    }
}
