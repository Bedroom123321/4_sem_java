package com.myapp.transportlogistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;


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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "driver_truck",
            joinColumns = @JoinColumn(name = "truck_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id")
    )
    private Set<Driver> drivers;


    public Truck() {
    }

    public Truck(Long id, Set<Driver> drivers, String cargoType,
                 int cargoVolume, int liftingCapacity, String numberPlate) {
        this.id = id;
        this.drivers = drivers;
        this.cargoType = cargoType;
        this.cargoVolume = cargoVolume;
        this.liftingCapacity = liftingCapacity;
        this.numberPlate = numberPlate;
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
