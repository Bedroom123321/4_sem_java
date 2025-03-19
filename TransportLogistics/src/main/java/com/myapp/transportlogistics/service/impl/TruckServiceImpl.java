package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import com.myapp.transportlogistics.mapper.TruckMapper;
import com.myapp.transportlogistics.model.Truck;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.TruckService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TruckServiceImpl implements TruckService {
    private final TruckRepository truckRepository;
    private final TruckMapper truckMapper;

    public TruckServiceImpl(TruckRepository truckRepository, TruckMapper truckMapper) {
        this.truckRepository = truckRepository;
        this.truckMapper = truckMapper;
    }

    @Override
    public TruckResponseDto findById(Long id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if (optionalTruck.isEmpty()) {
            throw new IllegalStateException("Транспорта с id " + id + " нет в базе");
        }

        Truck truck = optionalTruck.get();
        return truckMapper.toDto(truck);
    }

    @Override
    public List<TruckResponseDto> findAllTrucks() {
        List<Truck> trucks = truckRepository.findAll();
        return truckMapper.toDtoList(trucks);
    }

    @Override
    public TruckResponseDto create(TruckRequestDto truckRequestDto) {
        Optional<Truck> optionalTruck =
                truckRepository.findByNumberPlate(truckRequestDto.getNumberPlate());
        if (optionalTruck.isPresent()) {
            throw new IllegalStateException("Транспорт с таким номером уже существует");
        }

        Truck truck = truckMapper.toEntity(truckRequestDto);
        return truckMapper.toDto(truckRepository.save(truck));
    }

    @Override
    public void delete(Long id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if (optionalTruck.isEmpty()) {
            throw new IllegalStateException("Транспорта с id " + id + " нет в базе");
        }
        truckRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String cargoType, int cargoVolume) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        if (optionalTruck.isEmpty()) {
            throw new IllegalStateException("Транспорта с id " + id + " нет в базе");
        }

        Truck truck = optionalTruck.get();

        if (cargoType != null && !cargoType.equals(truck.getCargoType())) {
            truck.setCargoType(cargoType);
        }

        if (cargoVolume != 0 && cargoVolume != truck.getCargoVolume()) {
            truck.setCargoVolume(cargoVolume);
        }

        truckRepository.save(truck);
    }

}
