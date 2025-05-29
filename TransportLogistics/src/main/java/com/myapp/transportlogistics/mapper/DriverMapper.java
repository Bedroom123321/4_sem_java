package com.myapp.transportlogistics.mapper;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import com.myapp.transportlogistics.model.Driver;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public Driver toEntity(DriverRequestDto dto) {
        Driver driver = new Driver();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setWorkExperience(dto.getWorkExperience());
        return driver;
    }

    public DriverResponseDto toDto(Driver driver) {
        DriverResponseDto dto = new DriverResponseDto();
        dto.setId(driver.getId());
        dto.setFirstName(driver.getFirstName());
        dto.setLastName(driver.getLastName());
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

    public void updateEntityFromDto(DriverRequestDto dto, Driver driver) {
        // Обновляем только те поля, которые пришли в DTO, сохраняя существующий id
        if (dto.getFirstName() != null) {
            driver.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            driver.setLastName(dto.getLastName());
        }
        if (dto.getPhoneNumber() != null) {
            driver.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getWorkExperience() != null) {
            driver.setWorkExperience(dto.getWorkExperience());
        }
    }
}