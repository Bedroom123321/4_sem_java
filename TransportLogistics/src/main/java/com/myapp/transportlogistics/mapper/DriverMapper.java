package com.myapp.transportlogistics.mapper;

import com.myapp.transportlogistics.dto.DriverRequestDto;
import com.myapp.transportlogistics.dto.DriverResponseDto;
import com.myapp.transportlogistics.model.Driver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DriverMapper {

    public Driver toEntity(DriverRequestDto dto) {
        Driver driver = new Driver();
        driver.setName(dto.getName());
        driver.setSecondName(dto.getSecondName());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setWorkExperience(dto.getWorkExperience());
        return driver;
    }

    public DriverResponseDto toDto(Driver driver) {
        DriverResponseDto dto = new DriverResponseDto();
        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setSecondName(driver.getSecondName());
        dto.setPhoneNumber(driver.getPhoneNumber());
        dto.setWorkExperience(driver.getWorkExperience());
        return dto;
    }

    public List<DriverResponseDto> toDtoList(List<Driver> drivers) {
        ArrayList<DriverResponseDto> driverResponseDtos = new ArrayList<>();
        for (Driver driver : drivers) {
            driverResponseDtos.add(toDto(driver));
        }
        return driverResponseDtos;
    }
}
