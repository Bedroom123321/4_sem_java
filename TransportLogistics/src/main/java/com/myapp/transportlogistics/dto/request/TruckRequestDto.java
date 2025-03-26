package com.myapp.transportlogistics.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TruckRequestDto {
    private String numberPlate;
    private int liftingCapacity;
    private int cargoVolume;
    private String cargoType;
}
