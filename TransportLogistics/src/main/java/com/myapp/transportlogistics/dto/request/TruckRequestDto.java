package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TruckRequestDto {

    @Pattern(regexp = "^[A-Z]{2}\\d{5}$",
            message = "В начале серия, затем регистрационный номер, затем код регистрации")
    private String numberPlate;

    @Min(value = 1, message = "Грузоподъемность должна быть больше нуля(кг)")
    private int liftingCapacity;

    @Min(value = 1, message = "Объём груза должен быть больше нуля(кубические метры )")
    private int cargoVolume;

    @NotBlank(message = "Тип груза должен быть заполнен")
    private String cargoType;


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
}
