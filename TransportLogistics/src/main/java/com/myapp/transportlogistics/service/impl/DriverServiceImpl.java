package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.cache.Cache;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DriverServiceImpl implements DriverService {

    DriverRepository driverRepository;
    TruckRepository truckRepository;
    OrderServiceImpl orderServiceImpl;
    DriverMapper driverMapper;
    Cache<Long, Driver> cache;

    @Override
    @Transactional
    public DriverResponseDto findById(Long id) {
        Optional<Driver> cachedDriver = cache.get(id);
        if (cachedDriver.isPresent()) {
            return driverMapper.toDto(cachedDriver.get());
        }

        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException();
        }

        cache.put(id, optionalDriver.get());
        return driverMapper.toDto(optionalDriver.get());
    }

    @Override
    @Transactional
    public List<DriverResponseDto> findAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();

        return driverMapper.toDtoList(drivers);
    }

    @Override
    @Transactional
    public DriverResponseDto create(DriverRequestDto driverRequestDto) {
        Optional<Driver> optionalDriver =
                driverRepository.findByPhoneNumber(driverRequestDto.getPhoneNumber());
        if (optionalDriver.isPresent()) {
            throw new IllegalStateException();
        }

        Driver driver = driverMapper.toEntity(driverRequestDto);
        Driver savedDriver = driverRepository.save(driver);

        cache.put(savedDriver.getId(), savedDriver);
        return driverMapper.toDto(savedDriver);
    }

    @Override
    public void delete(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException();
        }

        orderServiceImpl.setDriverToNull(id);
        driverRepository.deleteById(id);

        cache.remove(id);
    }

    @Override
    @Transactional
    public void update(Long id, String secondName, String phoneNumber) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException();
        }

        Driver driver = optionalDriver.get();

        if (secondName != null && !secondName.equals(driver.getLastName())) {
            driver.setLastName(secondName);
        }

        if (phoneNumber != null && !phoneNumber.equals(driver.getPhoneNumber())) {
            driver.setPhoneNumber(phoneNumber);
        }

        driverRepository.save(driver);
        cache.put(driver.getId(), driver);
    }

    @Override
    @Transactional
    public List<DriverResponseDto> getDriversByTruckId(Long truckId) {
        Optional<Truck> optionalTruck = truckRepository.findById(truckId);
        if (optionalTruck.isEmpty()) {
            throw new IllegalStateException();
        }

        List<Driver> drivers = driverRepository.getDriversByTruckId(truckId);
        return driverMapper.toDtoList(drivers);
    }
}