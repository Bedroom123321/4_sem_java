package com.myapp.transportlogistics.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckResponseDto {
    private Long id;
    private String numberPlate;
    private int liftingCapacity;
    private int cargoVolume;
    private String cargoType;


    public TruckResponseDto() {

    }

    public TruckResponseDto(long id, String numberPlate,
                            int liftingCapacity, int cargoVolume, String cargoType) {
        this.id = id;
        this.cargoVolume = cargoVolume;
        this.numberPlate = numberPlate;
        this.liftingCapacity = liftingCapacity;
        this.cargoType = cargoType;
    }
}
