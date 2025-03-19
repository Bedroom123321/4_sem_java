package com.myapp.transportlogistics.mapper;

import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import com.myapp.transportlogistics.model.Truck;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TruckMapper {

    public Truck toEntity(TruckRequestDto dto) {
        Truck truck = new Truck();
        truck.setNumberPlate(dto.getNumberPlate());
        truck.setLiftingCapacity(dto.getLiftingCapacity());
        truck.setCargoVolume(dto.getCargoVolume());
        truck.setCargoType(dto.getCargoType());
        return truck;
    }

    public TruckResponseDto toDto(Truck truck) {
        TruckResponseDto dto = new TruckResponseDto();
        dto.setId(truck.getId());
        dto.setNumberPlate(truck.getNumberPlate());
        dto.setLiftingCapacity(truck.getLiftingCapacity());
        dto.setCargoVolume(truck.getCargoVolume());
        dto.setCargoType(truck.getCargoType());
        return dto;
    }

    public List<TruckResponseDto> toDtoList(List<Truck> trucks) {
        ArrayList<TruckResponseDto> truckResponseDtos = new ArrayList<>();
        for (Truck truck : trucks) {
            truckResponseDtos.add(toDto(truck));
        }
        return truckResponseDtos;
    }
}