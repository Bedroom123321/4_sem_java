package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.DriverRequestDto;
import com.myapp.transportlogistics.dto.DriverResponseDto;
import com.myapp.transportlogistics.mapper.DriverMapper;
import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.repository.DriversRepository;
import com.myapp.transportlogistics.service.DriverService;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriversRepository driversRepository;
    private final DriverMapper driverMapper;

    public DriverServiceImpl(DriversRepository driversRepository, DriverMapper driverMapper) {
        this.driversRepository = driversRepository;
        this.driverMapper = driverMapper;
    }

    @Override
    public DriverResponseDto findById(Long id) {
        Optional<Driver> optionalDriver = driversRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + id + " нет в базе");
        }
        return driverMapper.toDto(optionalDriver.get());
    }

    @Override
    public List<DriverResponseDto> findAllDrivers() {
        List<Driver> drivers = driversRepository.findAll();
        return driverMapper.toDtoList(drivers);
    }

    @Override
    public DriverResponseDto create(DriverRequestDto driverRequestDto) {
        Optional<Driver> optionalDriver =
                driversRepository.findByPhoneNumber(driverRequestDto.getPhoneNumber());
        if (optionalDriver.isPresent()) {
            throw new IllegalStateException("Водитель с таким номером телефона уже зарегистрирован");
        }

        Driver driver = driverMapper.toEntity(driverRequestDto);
        Driver savedDriver = driversRepository.save(driver);
        return driverMapper.toDto(savedDriver);
    }

    @Override
    public void delete(Long id) {
        Optional<Driver> optionalDriver = driversRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + id + " нет в базе");
        }
        driversRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String name, String secondName, String phoneNumber) {
        Optional<Driver> optionalDriver = driversRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + id + " нет в базе");
        }

        Driver driver = optionalDriver.get();

        if (name != null && !name.equals(driver.getName())) {
            driver.setName(name);
        }

        if (secondName != null && !secondName.equals(driver.getSecondName())) {
            driver.setSecondName(secondName);
        }

        if (phoneNumber != null && !phoneNumber.equals(driver.getPhoneNumber())) {
            driver.setPhoneNumber(phoneNumber);
        }

        driversRepository.save(driver);
    }
}
