package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import com.myapp.transportlogistics.mapper.DriverMapper;
import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.model.Truck;
import com.myapp.transportlogistics.repository.DriverRepository;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.DriverService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final TruckRepository truckRepository;

    public DriverServiceImpl(DriverRepository driverRepository, DriverMapper driverMapper, TruckRepository truckRepository) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.truckRepository = truckRepository;
    }

    @Override
    public DriverResponseDto findById(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + id + " нет в базе");
        }
        return driverMapper.toDto(optionalDriver.get());
    }

    @Override
    public List<DriverResponseDto> findAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return driverMapper.toDtoList(drivers);
    }

    @Override
    public DriverResponseDto create(DriverRequestDto driverRequestDto) {
        Optional<Driver> optionalDriver =
                driverRepository.findByPhoneNumber(driverRequestDto.getPhoneNumber());
        if (optionalDriver.isPresent()) {
            throw new IllegalStateException("Водитель с таким номером "
                    + "телефона уже зарегистрирован");
        }

        Driver driver = driverMapper.toEntity(driverRequestDto);
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toDto(savedDriver);
    }

    @Override
    public void delete(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + id + " нет в базе");
        }
        driverRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String secondName, String phoneNumber) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + id + " нет в базе");
        }

        Driver driver = optionalDriver.get();

        if (secondName != null && !secondName.equals(driver.getSecondName())) {
            driver.setSecondName(secondName);
        }

        if (phoneNumber != null && !phoneNumber.equals(driver.getPhoneNumber())) {
            driver.setPhoneNumber(phoneNumber);
        }

        driverRepository.save(driver);
    }

    @Transactional
    public void assignTruckToDriver(Long driverId, Long truckId) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + driverId + " нет в базе");
        }
        Optional<Truck> optionalTruck = truckRepository.findById(truckId);
        if (optionalTruck.isEmpty()) {
            throw new IllegalStateException("Транспорта с id " + truckId + " нет в базе");
        }

        Driver driver = optionalDriver.get();
        Truck truck = optionalTruck.get();

        driver.setTruck(truck);
        truck.setDriver(driver);

        driverRepository.save(driver);
    }
}
