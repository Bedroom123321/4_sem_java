package com.myapp.transportlogistics.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TruckResponseDto {
    private Long id;
    private String numberPlate;
    private int liftingCapacity;
    private int cargoVolume;
    private String cargoType;
}
