package com.myapp.transportlogistics.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numberplate")
    private String numberPlate;
    @Column(name = "liftingcapacity")
    private int liftingCapacity;
    @Column(name = "cargovolume")
    private int cargoVolume;
    @Column(name = "cargotype")
    private String cargoType;

    public Truck() {
    }

    public Truck(String numberPlate, Long id, int liftingCapacity,
                 int cargoVolume, String cargoType) {
        this.numberPlate = numberPlate;
        this.id = id;
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
