package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.cache.Cache;
import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import com.myapp.transportlogistics.exception.EntityAlreadyExistsException;
import com.myapp.transportlogistics.exception.EntityNotFoundException;
import com.myapp.transportlogistics.mapper.DriverMapper;
import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.model.Truck;
import com.myapp.transportlogistics.repository.DriverRepository;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.DriverService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;
    private final OrderServiceImpl orderServiceImpl;
    private final DriverMapper driverMapper;
    private final Cache<Long, Driver> cache;

    @Override
    @Transactional
    public DriverResponseDto findById(Long id) {
        Optional<Driver> cachedDriver = cache.get(id);
        if (cachedDriver.isPresent()) {
            return driverMapper.toDto(cachedDriver.get());
        }

        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new EntityNotFoundException("Водитель с таким ID не найден");
        } else {
            cache.put(id, optionalDriver.get());
            return driverMapper.toDto(optionalDriver.get());
        }
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
            throw new EntityAlreadyExistsException("Такой водитель уже существует");
        }

        Driver driver = driverMapper.toEntity(driverRequestDto);
        Driver savedDriver = driverRepository.save(driver);

        cache.put(savedDriver.getId(), savedDriver);
        return driverMapper.toDto(savedDriver);
    }

    @Override
    @Transactional
    public List<DriverResponseDto> addDrivers(List<DriverRequestDto> driverRequestDtos) {

        List<Driver> newDrivers = driverRequestDtos.stream()
                .distinct()
                .filter(driver -> driverRepository
                        .findByPhoneNumber(driver.getPhoneNumber()).isEmpty())
                .map(driverMapper::toEntity)
                .toList();

        List<Driver> savedDrivers = driverRepository.saveAll(newDrivers);

        return savedDrivers.stream()
                .map(driverMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new EntityNotFoundException("Водитель с таким ID не найден");
        }

        orderServiceImpl.setDriverToNull(id);
        driverRepository.deleteById(id);

        cache.remove(id);
    }

    @Override
    @Transactional
    public DriverResponseDto update(Long id, DriverRequestDto driverRequestDto) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new EntityNotFoundException("Водитель с таким ID не найден");
        }

        Driver driver = optionalDriver.get();
        driverMapper.updateEntityFromDto(driverRequestDto, driver);
        Driver updatedDriver = driverRepository.save(driver);

        return driverMapper.toDto(updatedDriver);
    }

    @Override
    @Transactional
    public List<DriverResponseDto> getDriversByTruckId(Long truckId) {
        Optional<Truck> optionalTruck = truckRepository.findById(truckId);
        if (optionalTruck.isEmpty()) {
            throw new EntityNotFoundException("Транспорт с таким ID не найден");
        }

        List<Driver> drivers = driverRepository.getDriversByTruckId(truckId);
        return driverMapper.toDtoList(drivers);
    }

}