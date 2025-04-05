package com.myapp.transportlogistics.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TruckResponseDto {
    Long id;
    String numberPlate;
    int liftingCapacity;
    int cargoVolume;
    String cargoType;
}
