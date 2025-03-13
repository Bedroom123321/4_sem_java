package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.model.Driver;
import java.util.List;

public interface DriverService {
    List<Driver> findAllDrivers();

    Driver creat(Driver driver);

    void delete(Long id);

    void update(Long id, String name, String secondName, String phoneNumber);
}
