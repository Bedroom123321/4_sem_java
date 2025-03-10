package com.myapp.transportlogistics.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    private int id;
    private String numberPlate;
    private int liftingCapacity;
    private int cargoVolume;
    private String cargoType;

    public Truck() {
    }

    public Truck(String numberPlate, int id, int liftingCapacity, int cargoVolume, String cargoType) {
        this.numberPlate = numberPlate;
        this.id = id;
        this.liftingCapacity = liftingCapacity;
        this.cargoVolume = cargoVolume;
        this.cargoType = cargoType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", numberPlate='" + numberPlate + '\'' +
                ", liftingCapacity=" + liftingCapacity +
                ", cargoVolume=" + cargoVolume +
                ", cargoType='" + cargoType + '\'' +
                '}';
    }
}
