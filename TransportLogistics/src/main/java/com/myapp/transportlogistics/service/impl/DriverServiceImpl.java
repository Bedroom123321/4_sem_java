package com.myapp.transportlogistics.service.impl;

import com.myapp.transportlogistics.model.Driver;
import com.myapp.transportlogistics.repository.DriversRepository;
import com.myapp.transportlogistics.service.DriverService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriversRepository driversRepository;

    public DriverServiceImpl(DriversRepository driversRepository) {
        this.driversRepository = driversRepository;
    }

    @Override
    public List<Driver> findAllDrivers() {
        return driversRepository.findAll();
    }

    @Override
    public Driver creat(Driver driver) {
        Optional<Driver> optionalDriver =
                driversRepository.findByPhoneNumber(driver.getPhoneNumber());
        if (optionalDriver.isPresent()) {
            throw new IllegalStateException("Водитель с таким номером телефона"
                                            + " уже зарегестрирован");
        }
        return driversRepository.save(driver);
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
    public void update(Long id, String name, String secondName, String phoneNumber) {
        Optional<Driver> optionalDriver = driversRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Транспорта с id " + id + " нет в базе");
        }
        Driver driver = optionalDriver.get();

        if (name != null && !name.equals(driver.getName())) {
            driver.setName(name);
        }

        if (secondName != null && secondName.equals(driver.getSecondName())) {
            driver.setSecondName(secondName);
        }

        driversRepository.save(driver);
    }
}
