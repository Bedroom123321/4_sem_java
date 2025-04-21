package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

public class TruckRequestDto {

    @Pattern(regexp = "^[A-Za-z]{2}\\d{5}$",
            message = "Некорректный автомобильный номер (пример AB1245)")
    private String numberPlate;

    @Min(value = 3500, message = "Грузоподъёмнасть должна быть минимум 3500")
    @Pattern(regexp = "\\d+", message = "Грузоподъёмнасть должна быть числом")
    private int liftingCapacity;

    @Pattern(regexp = "\\d+", message = "Объём груза должен быть числом")
    private int cargoVolume;

    @NotBlank(message = "Тип груза обязателен")
    private String cargoType;

    public TruckRequestDto() {

    }

    public TruckRequestDto(String numberPlate, int liftingCapacity,
                           int cargoVolume, String cargoType) {
        this.cargoVolume = cargoVolume;
        this.numberPlate = numberPlate;
        this.liftingCapacity = liftingCapacity;
        this.cargoType = cargoType;
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
}
