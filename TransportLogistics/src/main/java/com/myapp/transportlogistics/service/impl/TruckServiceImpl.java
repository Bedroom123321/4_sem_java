package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import com.myapp.transportlogistics.exception.EntityAlreadyExistsException;
import com.myapp.transportlogistics.exception.EntityNotFoundException;
import com.myapp.transportlogistics.mapper.TruckMapper;
import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.model.Truck;
import com.myapp.transportlogistics.repository.DriverRepository;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.TruckService;
import com.myapp.transportlogistics.service.VisitorCounter;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;
    private final DriverRepository driverRepository;
    private final OrderServiceImpl orderServiceImpl;
    private final TruckMapper truckMapper;
    private final VisitorCounter counter;

    @Override
    @Transactional
    public TruckResponseDto findById(Long id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if (optionalTruck.isEmpty()) {
            throw new EntityNotFoundException("Транспорт с таким ID не найден");
        }

        Truck truck = optionalTruck.get();
        return truckMapper.toDto(truck);
    }

    @Override
    @Transactional
    public List<TruckResponseDto> findAllTrucks() {
        List<Truck> trucks = truckRepository.findAll();
        counter.increment();
        return truckMapper.toDtoList(trucks);
    }

    @Override
    @Transactional
    public TruckResponseDto addTruck(TruckRequestDto truckRequestDto) {
        Optional<Truck> optionalTruck =
                truckRepository.findByNumberPlate(truckRequestDto.getNumberPlate());
        if (optionalTruck.isPresent()) {
            throw new EntityAlreadyExistsException("Такой транспорт уже существует");
        }

        Truck truck = truckMapper.toEntity(truckRequestDto);
        return truckMapper.toDto(truckRepository.save(truck));
    }

    @Override
    @Transactional
    public List<TruckResponseDto> addTrucks(List<TruckRequestDto> truckRequestDtos) {
        List<Truck> newTrucks = truckRequestDtos.stream()
                .distinct()
                .filter(truck -> truckRepository
                        .findByNumberPlate(truck.getNumberPlate()).isEmpty())
                .map(truckMapper::toEntity)
                .toList();

        List<Truck> savedTrucks = truckRepository.saveAll(newTrucks);

        return savedTrucks.stream()
                .map(truckMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if (optionalTruck.isEmpty()) {
            throw new EntityNotFoundException("Транспорт с таким ID не найден");
        }

        orderServiceImpl.setTruckToNull(id);
        truckRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TruckResponseDto update(Long id, TruckRequestDto truckRequestDto) {
        System.out.println("Updating truck ID: " + id + ", data: " + truckRequestDto);
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if (optionalTruck.isEmpty()) {
            throw new EntityNotFoundException("Транспорт с таким ID не найден");
        }

        Truck truck = optionalTruck.get();
        Optional<Truck> existingTruck = truckRepository.findByNumberPlate(truckRequestDto.getNumberPlate());
        if (existingTruck.isPresent() && !existingTruck.get().getId().equals(id)) {
            throw new EntityAlreadyExistsException("Грузовик с таким номером уже существует");
        }

        truck.setNumberPlate(truckRequestDto.getNumberPlate());
        truck.setLiftingCapacity(truckRequestDto.getLiftingCapacity());
        truck.setCargoVolume(truckRequestDto.getCargoVolume());
        truck.setCargoType(truckRequestDto.getCargoType());

        Truck updatedTruck = truckRepository.save(truck);
        return truckMapper.toDto(updatedTruck);
    }

    @Override
    @Transactional
    public List<TruckResponseDto> getTrucksByDriverId(Long driverId) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isEmpty()) {
            throw new EntityNotFoundException("Водитель с таким ID не найден");
        }

        List<Truck> trucks = truckRepository.getTrucksByDriverId(driverId);
        return truckMapper.toDtoList(trucks);
    }
}