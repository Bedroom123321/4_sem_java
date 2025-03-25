package com.myapp.transportlogistics.model;

import jakarta.persistence.*;

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

    @OneToOne(mappedBy = "truck",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Driver driver;

    public Truck() {
    }

    public Truck(String numberPlate, int liftingCapacity,
                 int cargoVolume, String cargoType) {
        this.numberPlate = numberPlate;
        this.liftingCapacity = liftingCapacity;
        this.cargoVolume = cargoVolume;
        this.cargoType = cargoType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public int getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public int getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(int liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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
}
