package com.myapp.transportlogistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Temporal(TemporalType.DATE)
    Date orderDate;
    String loadingPoint;
    String deliveryPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id", nullable = true)
    Truck truck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = true)
    Driver driver;

    public Order() {
    }

    public Order(Date orderDate, String loadingPoint,
                 String deliveryPoint) {
        this.orderDate = orderDate;
        this.loadingPoint = loadingPoint;
        this.deliveryPoint = deliveryPoint;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", orderDate=" + (orderDate != null ? orderDate : "null")
                + ", loadingPoint='" + loadingPoint + '\''
                + ", deliveryPoint='" + deliveryPoint + '\''
                + ", clientId=" + (client != null ? client.getId() : "null")
                + ", driverId=" + (driver != null ? driver.getId() : "null")
                + '}';
    }
}
