package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckRequestDto {

    @Pattern(regexp = "^[A-Za-z]{2}\\d{5}$",
            message = "Некорректный автомобильный номер (пример AB1245)")
    private String numberPlate;

    @Min(value = 3500, message = "Грузоподъёмность должна быть минимум 3500")
    private int liftingCapacity;

    @Min(value = 0, message = "Объём груза должен быть неотрицательным")
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
}
