package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.mapper.TruckMapper;
import com.myapp.transportlogistics.repository.TruckRepository;
import com.myapp.transportlogistics.service.impl.TruckServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TruckServiceImplTest {
    @InjectMocks
    private TruckServiceImpl truckServiceImpl;
    @Mock
    private TruckRepository truckRepository;
    @Mock
    private TruckMapper truckMapper;
    
    @Test
    void findById() {
    }

    @Test
    void findAllTrucks() {
    }

    @Test
    void addTruck() {
    }

    @Test
    void addTrucks() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getTrucksByDriverId() {
    }
}