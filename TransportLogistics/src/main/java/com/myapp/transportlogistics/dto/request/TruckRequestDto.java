package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TruckRequestDto {

    @Pattern(regexp = "^[A-Za-z]{2}[0-9]{5}$",
            message = "Некорректный автомобильный номер (пример AB1245)")
    String numberPlate;

    @Min(value = 3500, message = "Грузоподъёмнасть должна быть минимум 3500")
    @Pattern(regexp = "\\d+", message = "Грузоподъёмнасть должна быть числом")
    int liftingCapacity;

    @Pattern(regexp = "\\d+", message = "Объём груза должен быть числом")
    int cargoVolume;

    @NotBlank(message = "Тип груза обязателен")
    String cargoType;
}
