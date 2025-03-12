package com.myapp.transportlogistics.service;

import com.myapp.transportlogistics.models.Driver;
import com.myapp.transportlogistics.repository.DriversRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class DriverService {
    private final DriversRepository driversRepository;

    public DriverService(DriversRepository driversRepository) {
        this.driversRepository = driversRepository;
    }

    public List<Driver> findAlldrivers() {
        return driversRepository.findAll();
    }

    public Driver creat(Driver driver) {
        Optional<Driver> optionalDriver = driversRepository.findByPhoneNumber(driver.getPhoneNumber());
        if (optionalDriver.isPresent()) {
            throw new IllegalStateException("Вадитель с таким номером телефона уже зарегестрирован");
        }
        return driversRepository.save(driver);
    }

    public void delete(Long id) {
        Optional<Driver> optionalDriver = driversRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new IllegalStateException("Водителя с id " + id + " нет в базе");
        }
        driversRepository.deleteById(id);
    }

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
