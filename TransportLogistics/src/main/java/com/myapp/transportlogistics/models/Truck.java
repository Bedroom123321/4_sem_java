package com.myapp.transportlogistics.models;

public class Truck {
    private int id;
    private int liftingCapacity;
    private int cargoVolume;

    public Truck(int id, int cargoVolume, int liftingCapacity) {
        this.id = id;
        this.cargoVolume = cargoVolume;
        this.liftingCapacity = liftingCapacity;
    }

    public int getId() {
        return id;
    }

    public int getCargoVolume() {
        return cargoVolume;
    }

    public int getLiftingCapacity() {
        return liftingCapacity;
    }
}
