package com.myapp.transportlogistics.service;

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
import com.myapp.transportlogistics.service.impl.DriverServiceImpl;
import com.myapp.transportlogistics.service.impl.OrderServiceImpl;
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
class DriverServiceImplTest {

    @InjectMocks
    private DriverServiceImpl driverServiceImpl;
    @Mock
    private OrderServiceImpl orderServiceImpl;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private TruckRepository truckRepository;
    @Mock
    private DriverMapper driverMapper;
    @Mock
    private Cache<Long, Driver> cache;

    private Driver driver1;
    private Driver driver2;
    private DriverResponseDto driverResponseDto1;
    private DriverResponseDto driverResponseDto2;
    private DriverRequestDto driverRequestDto1;
    private DriverRequestDto driverRequestDto2;
    private final long firstDriverId = 1L;
    private final String newPhoneNumber = "+375293332211";

    @BeforeEach
    void setUp() {
        driver1 = new Driver(1L, "Роман"
                , "Бадестов", "+375291184712", "5 years");

        driverRequestDto1 = new DriverRequestDto("Роман"
                , "Бадестов", "+375291184712", "5 years");

        driverResponseDto1 = new DriverResponseDto(1L, "Роман"
                , "Бадестов", "+375291184712", "5 years");

        driver2 = new Driver(2L, "Илья"
                , "Ходин", "+375333916665","3 years");

        driverRequestDto2 = new DriverRequestDto("Илья"
                , "Ходин", "+375333916665","3 years");

        driverResponseDto2 = new DriverResponseDto(2L, "Илья"
                , "Ходин", "+375333916665", "3 years");
    }

    @Test
    void testFindById_hittingCache() {
        Mockito.when(cache.get(firstDriverId)).thenReturn(Optional.of(driver1));
        Mockito.when(driverMapper.toDto(driver1)).thenReturn(driverResponseDto1);

        DriverResponseDto result = driverServiceImpl.findById(firstDriverId);

        Assertions.assertEquals(driverResponseDto1, result);
    }

    @Test
    void testFindById_missingCache() {
        Mockito.when(cache.get(firstDriverId)).thenReturn(Optional.empty());
        Mockito.when(driverRepository.findById(firstDriverId)).thenReturn(Optional.of(driver1));
        Mockito.when(driverMapper.toDto(driver1)).thenReturn(driverResponseDto1);

        DriverResponseDto result = driverServiceImpl.findById(firstDriverId);

        Assertions.assertEquals(driverResponseDto1, result);
    }

    @Test
    void testFindById_notFoundException() {
        Mockito.when(cache.get(firstDriverId)).thenReturn(Optional.empty());
        Mockito.when(driverRepository.findById(firstDriverId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> driverServiceImpl.findById(firstDriverId));
    }

    @Test
    void testFindAllDrivers() {
        Mockito.when(driverRepository.findAll()).thenReturn(List.of(driver1, driver2));
        Mockito.when(driverMapper.toDtoList(List.of(driver1, driver2)))
                .thenReturn(List.of(driverResponseDto1,driverResponseDto2));

        List<DriverResponseDto> result = driverServiceImpl.findAllDrivers();

        Assertions.assertEquals(List.of(driverResponseDto1,driverResponseDto2), result);
    }

    @Test
    void testCreate() {
        Mockito.when(driverMapper.toEntity(driverRequestDto1)).thenReturn(driver1);
        Mockito.when(driverRepository.save(driver1)).thenReturn(driver1);
        Mockito.when(driverMapper.toDto(driver1)).thenReturn(driverResponseDto1);

        DriverResponseDto result = driverServiceImpl.create(driverRequestDto1);

        Assertions.assertEquals(driverResponseDto1, result);
        Mockito.verify(cache, Mockito.times(1)).put(firstDriverId,driver1);
    }

    @Test
    void testCreate_alreadyExistsException() {
        Mockito.when(driverRepository.findByPhoneNumber(driverRequestDto1.getPhoneNumber()))
                .thenReturn(Optional.of(driver1));

        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> driverServiceImpl.create(driverRequestDto1));
    }

    @Test
    void addDrivers() {
        Mockito.when(driverRepository.findByPhoneNumber(driverRequestDto1.getPhoneNumber())).thenReturn(Optional.empty());
        Mockito.when(driverRepository.findByPhoneNumber(driverRequestDto2.getPhoneNumber())).thenReturn(Optional.empty());

        Mockito.when(driverMapper.toEntity(driverRequestDto1)).thenReturn(driver1);
        Mockito.when(driverMapper.toEntity(driverRequestDto2)).thenReturn(driver2);

        Mockito.when(driverRepository.saveAll(List.of(driver1,driver2))).thenReturn(List.of(driver1,driver2));

        Mockito.when(driverMapper.toDto(driver1)).thenReturn(driverResponseDto1);
        Mockito.when(driverMapper.toDto(driver2)).thenReturn(driverResponseDto2);

        List<DriverResponseDto> result = driverServiceImpl.addDrivers(List.of(driverRequestDto1,driverRequestDto2));

        Assertions.assertEquals(List.of(driverResponseDto1,driverResponseDto2), result);
    }

    @Test
    void testAddDrivers_clientAlreadyExists() {

        Mockito.when(driverRepository.findByPhoneNumber(driverRequestDto1.getPhoneNumber())).thenReturn(Optional.of(driver1));
        Mockito.when(driverRepository.findByPhoneNumber(driverRequestDto2.getPhoneNumber())).thenReturn(Optional.empty());

        Mockito.when(driverMapper.toEntity(driverRequestDto2)).thenReturn(driver2);

        Mockito.when(driverRepository.saveAll(List.of(driver2))).thenReturn(List.of(driver2));

        Mockito.when(driverMapper.toDto(driver2)).thenReturn(driverResponseDto2);

        List<DriverResponseDto> result = driverServiceImpl.addDrivers(List.of(driverRequestDto1, driverRequestDto2));

        Assertions.assertEquals(List.of(driverResponseDto2), result);
    }

    @Test
    void delete() {
        Mockito.when(driverRepository.findById(firstDriverId)).thenReturn(Optional.of(driver1));

        driverServiceImpl.delete(firstDriverId);

        Mockito.verify(driverRepository, Mockito.times(1)).deleteById(firstDriverId);
        Mockito.verify(orderServiceImpl, Mockito.times(1)).setDriverToNull(firstDriverId);
    }
    @Test
    void testDelete_notFoundException() {
        Mockito.when(driverRepository.findById(firstDriverId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> driverServiceImpl.delete(firstDriverId));
    }

    @Test
    void update() {Mockito.when(driverRepository.findById(firstDriverId)).thenReturn(Optional.of(driver1));

        driverServiceImpl.update(firstDriverId, newPhoneNumber);

        Assertions.assertEquals(newPhoneNumber,driver1.getPhoneNumber());
        Mockito.verify(driverRepository, Mockito.times(1)).save(driver1);

    }

    @Test
    void testUpdate_notFoundException() {
        Mockito.when(driverRepository.findById(firstDriverId)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> driverServiceImpl.update(firstDriverId, newPhoneNumber));
    }

    @Test
    void getDriversByTruckId() {
        long truckId = 3L;
        Mockito.when(truckRepository.findById(truckId)).thenReturn(Optional.of(new Truck()));
        Mockito.when(driverRepository.getDriversByTruckId(truckId)).thenReturn(List.of(driver1,driver2));
        Mockito.when(driverMapper.toDtoList(List.of(driver1,driver2))).thenReturn(List.of(driverResponseDto1,driverResponseDto2));

        List<DriverResponseDto> result = driverServiceImpl.getDriversByTruckId(truckId);

        Assertions.assertEquals(List.of(driverResponseDto1,driverResponseDto2), result);
    }

    @Test
    void getDriversByTruckId_notFoundException() {
        long truckId = 3L;
        Mockito.when(truckRepository.findById(truckId)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> driverServiceImpl
                .getDriversByTruckId(truckId));
    }

}