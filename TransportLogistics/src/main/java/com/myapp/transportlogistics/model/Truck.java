package com.myapp.transportlogistics.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String numberPlate;
    private int liftingCapacity;
    private int cargoVolume;
    private String cargoType;

    @OneToMany(mappedBy = "truck", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Order> orders = new ArrayList<>();

    public Truck() {
    }

    public Truck(long id, String numberPlate, int liftingCapacity,
                 int cargoVolume, String cargoType) {
        this.id = id;
        this.numberPlate = numberPlate;
        this.liftingCapacity = liftingCapacity;
        this.cargoVolume = cargoVolume;
        this.cargoType = cargoType;
    }

    @Override
    public String toString() {
        return "Truck{"
                + "id=" + id
                + ", numberPlate='" + numberPlate + '\''
                + ", liftingCapacity=" + liftingCapacity
                + ", cargoVolume=" + cargoVolume
                + ", cargoType='" + cargoType + '\''
                + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public int getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(int liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }

    public int getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
