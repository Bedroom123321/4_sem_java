package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import com.myapp.transportlogistics.exception.EntityAlreadyExistsException;
import com.myapp.transportlogistics.exception.EntityNotFoundException;
import com.myapp.transportlogistics.mapper.TruckMapper;
import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.model.Truck;
import com.myapp.transportlogistics.repository.DriverRepository;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.impl.OrderServiceImpl;
import com.myapp.transportlogistics.service.impl.TruckServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TruckServiceImplTest {
    @InjectMocks
    private TruckServiceImpl truckServiceImpl;
    @Mock
    private OrderServiceImpl orderServiceImpl;
    @Mock
    private TruckRepository truckRepository;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private TruckMapper truckMapper;
    @Mock
    private Truck truck1;

    private final long firstTruckId = 1L;
    private TruckRequestDto truckRequestDto1;
    private TruckResponseDto truckResponseDto1;
    private final long secondTruckId = 2L;
    private Truck truck2;
    private TruckRequestDto truckRequestDto2;
    private TruckResponseDto truckResponseDto2;
    private final String newCargoType = "Food";
    private final int newCargoVolume = 15;

    @BeforeEach
    void setUp() {
        truckRequestDto1 = new TruckRequestDto("AB12345", 5000
                , 30, "Furniture");
        truckResponseDto1 = new TruckResponseDto(1L,"AB12345", 5000
                , 30, "Furniture");
        truck2 = new Truck(2L, "LM78912", 8000
                , 16, "Gas");
        truckRequestDto2 = new TruckRequestDto("LM78912", 8000
                , 16, "Gas");
        truckResponseDto2 = new TruckResponseDto(2L, "LM78912", 8000
                , 16, "Gas");

    }

    @Test
    void testFindById() {
        Mockito.when(truckRepository.findById(firstTruckId)).thenReturn(Optional.of(truck1));
        Mockito.when(truckMapper.toDto(truck1)).thenReturn(truckResponseDto1);

        TruckResponseDto result = truckServiceImpl.findById(firstTruckId);

        Assertions.assertEquals(truckResponseDto1, result);
    }

    @Test
    void testFindById_notFoundException() {

        Mockito.when(truckRepository.findById(secondTruckId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> truckServiceImpl.findById(secondTruckId));
    }

    @Test
    void testFindAllTrucks() {
        Mockito.when(truckRepository.findAll()).thenReturn(List.of(truck1, truck2));
        Mockito.when(truckMapper.toDtoList(List.of(truck1, truck2)))
                .thenReturn(List.of(truckResponseDto1,truckResponseDto2));

        List<TruckResponseDto> result = truckServiceImpl.findAllTrucks();

        Assertions.assertEquals(List.of(truckResponseDto1,truckResponseDto2), result);
    }

    @Test
    void testAddTruck() {
        Mockito.when(truckMapper.toEntity(truckRequestDto1)).thenReturn(truck1);
        Mockito.when(truckRepository.save(truck1)).thenReturn(truck1);
        Mockito.when(truckMapper.toDto(truck1)).thenReturn(truckResponseDto1);

        TruckResponseDto result = truckServiceImpl.addTruck(truckRequestDto1);

        Assertions.assertEquals(truckResponseDto1, result);
    }

    @Test
    void testAddTruck_alreadyExistsException() {
        Mockito.when(truckRepository.findByNumberPlate(truckRequestDto1.getNumberPlate())).thenReturn(Optional.of(truck1));

        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> truckServiceImpl.addTruck(truckRequestDto1));
    }

    @Test
    void testAddTrucks() {
        Mockito.when(truckRepository.findByNumberPlate(truckRequestDto1.getNumberPlate())).thenReturn(Optional.empty());
        Mockito.when(truckRepository.findByNumberPlate(truckRequestDto2.getNumberPlate())).thenReturn(Optional.empty());

        Mockito.when(truckMapper.toEntity(truckRequestDto1)).thenReturn(truck1);
        Mockito.when(truckMapper.toEntity(truckRequestDto2)).thenReturn(truck2);

        Mockito.when(truckRepository.save(truck1)).thenReturn(truck1);
        Mockito.when(truckRepository.save(truck2)).thenReturn(truck2);

        Mockito.when(truckMapper.toDto(truck1)).thenReturn(truckResponseDto1);
        Mockito.when(truckMapper.toDto(truck2)).thenReturn(truckResponseDto2);

        List<TruckResponseDto> result = truckServiceImpl.addTrucks(List.of(truckRequestDto1,truckRequestDto2));

        Assertions.assertEquals(List.of(truckResponseDto1,truckResponseDto2), result);
    }

    @Test
    void testAddTrucks_truckAlreadyExists() {

        Mockito.when(truckRepository.findByNumberPlate(truckRequestDto1.getNumberPlate())).thenReturn(Optional.of(truck1));
        Mockito.when(truckRepository.findByNumberPlate(truckRequestDto2.getNumberPlate())).thenReturn(Optional.empty());
        Mockito.when(truckMapper.toEntity(truckRequestDto2)).thenReturn(truck2);
        Mockito.when(truckRepository.save(truck2)).thenReturn(truck2);
        Mockito.when(truckMapper.toDto(truck2)).thenReturn(truckResponseDto2);

        List<TruckResponseDto> result = truckServiceImpl.addTrucks(List.of(truckRequestDto1,truckRequestDto2));

        Assertions.assertEquals(List.of(truckResponseDto2), result);
    }

    @Test
    void testDelete() {
        Mockito.when(truckRepository.findById(firstTruckId)).thenReturn(Optional.of(truck1));

        truckServiceImpl.delete(firstTruckId);

        Mockito.verify(truckRepository, Mockito.times(1)).deleteById(firstTruckId);
        Mockito.verify(orderServiceImpl, Mockito.times(1)).setTruckToNull(firstTruckId);
    }

    @Test
    void testDelete_notFoundException() {
        Mockito.when(truckRepository.findById(secondTruckId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> truckServiceImpl.delete(secondTruckId));
    }

    @Test
    void testUpdate() {
        Mockito.when(truckRepository.findById(secondTruckId)).thenReturn(Optional.of(truck2));

        truckServiceImpl.update(secondTruckId, newCargoType, newCargoVolume);

        Assertions.assertEquals(newCargoVolume, truck2.getCargoVolume());
        Assertions.assertEquals(newCargoType,truck2.getCargoType());
        Mockito.verify(truckRepository, Mockito.times(1)).save(truck2);

    }

    @Test
    public void testUpdate_SameCargoTypeAndVolume() {

        Mockito.when(truckRepository.findById(secondTruckId)).thenReturn(Optional.of(truck2));

        truckServiceImpl.update(secondTruckId, truck2.getCargoType(), truck2.getCargoVolume());

        Mockito.verify(truckRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void testUpdate_NullCargoTypeAndVolume() {

        Mockito.when(truckRepository.findById(secondTruckId)).thenReturn(Optional.of(truck2));

        truckServiceImpl.update(secondTruckId, null,0);

        Mockito.verify(truckRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    void testUpdate_notFoundException() {
        Mockito.when(truckRepository.findById(secondTruckId)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> truckServiceImpl
                .update(secondTruckId, newCargoType,newCargoVolume));
    }

    @Test
    void getTrucksByDriverId() {
        long driverId = 3L;
        Mockito.when(driverRepository.findById(driverId)).thenReturn(Optional.of(new Driver()));
        Mockito.when(truckRepository.getTrucksByDriverId(driverId)).thenReturn(List.of(truck1,truck2));
        Mockito.when(truckMapper.toDtoList(List.of(truck1,truck2))).thenReturn(List.of(truckResponseDto1,truckResponseDto2));

        List<TruckResponseDto> result = truckServiceImpl.getTrucksByDriverId(driverId);

        Assertions.assertEquals(List.of(truckResponseDto1,truckResponseDto2), result);
    }

    @Test
    void getTrucksByDriverId_notFoundException() {
        long driverId = 3L;
        Mockito.when(driverRepository.findById(driverId)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> truckServiceImpl
                .getTrucksByDriverId(driverId));
    }
}