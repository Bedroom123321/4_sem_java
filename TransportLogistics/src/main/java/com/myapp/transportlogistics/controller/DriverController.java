package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import com.myapp.transportlogistics.exceprion.ValidationException;
import com.myapp.transportlogistics.service.impl.DriverServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Driver Controller")
@RestController
@RequestMapping("drivers")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DriverController {

    DriverServiceImpl driverServiceImpl;

    public DriverController(DriverServiceImpl driverService) {
        this.driverServiceImpl = driverService;
    }

    @Operation(summary = "Извлекает данные всех водителей",
            description = "Возвращает список всех водителей в базе в виде DTO ответа"
    )
    @GetMapping("get/all")
    public List<DriverResponseDto> getAllDrivers() {
        return driverServiceImpl.findAllDrivers();
    }

    @Operation(summary = "Извлекает данные водителя по его ID",
            description = "Получате ID водителя, отправляет в сервис и возвращает DTO ответа"
    )
    @GetMapping("get/{id}")
    public DriverResponseDto getDriverById(@PathVariable Long id) {
        idException(id);
        return driverServiceImpl.findById(id);
    }

    @Operation(summary = "Извлекает данные всех водителей, использующих конкретный транспорт",
            description = "Получате ID транспорта и возвращает список всех водителей в базе, "
                    + "использующих этот транспорт, в виде DTO ответа"
    )
    @GetMapping("get/by-truck/{truckId}")
    public List<DriverResponseDto> getDriversByTruckId(@PathVariable Long truckId) {
        idException(truckId);
        return driverServiceImpl.getDriversByTruckId(truckId);
    }

    @Operation(summary = "Создаёт нового водителя",
            description = "Принимает DTO запроса с данными водителя, сохраняет в базу и "
                    + "возвращает DTO ответа созданного водителя"
    )
    @PostMapping("post")
    public DriverResponseDto createDriver(@RequestBody DriverRequestDto driverRequestDto) {
        driverException(driverRequestDto);
        return driverServiceImpl.create(driverRequestDto);
    }

    @Operation(summary = "Удаляет водителя по его ID",
            description = "Принимает ID водителя и удаляет соответствующую запись из базы данных"
    )
    @DeleteMapping("delete/{id}")
    public void deleteDriver(@PathVariable Long id) {
        idException(id);
        driverServiceImpl.delete(id);
    }

    @Operation(summary = "Обновляет фамилию и номер телефона водителя",
            description = "Принимает ID водителя, а также данные, которые нужно"
                    + " изменить(фамилию и/или номер телефона), обновляет данные в базе данных"
    )
    @PutMapping("update/{id}")
    public void updateDriver(@PathVariable Long id,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String phoneNumber) {
        idException(id);
        lastNameException(lastName);
        phoneNumberException(phoneNumber);

        driverServiceImpl.update(id, lastName, phoneNumber);
    }

    private void idException(Long id) {

        if (id == null || id <= 0) {
            throw new ValidationException("ID должен быть больше нуля");
        }

    }

    private void phoneNumberException(String phoneNumber)throws ValidationException {

        String pattern = "^\\+375(17|25|29|33|44)\\d{7}$";
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || !phoneNumber.matches(pattern)) {
            throw new ValidationException(
                    "Номер должен быть в формате +375XXXXXXXXX (после кода оператора 7 цифр)");
        }

    }

    private void lastNameException(String lastName)throws ValidationException {

        if (lastName == null || lastName.isEmpty()) {
            throw new ValidationException("Фамилия водителя обязательна");
        }

    }

    private void driverException(DriverRequestDto driverRequestDto) {

        if (driverRequestDto.getFirstName() == null || driverRequestDto.getFirstName().isEmpty()) {
            throw new ValidationException("Имя водителя обязательно");
        }

        lastNameException(driverRequestDto.getLastName());

        phoneNumberException(driverRequestDto.getPhoneNumber());

        if (driverRequestDto.getWorkExperience() == null
                || driverRequestDto.getWorkExperience().isEmpty()) {
            throw new ValidationException("Стаж водителя обязателен");
        }
    }
}
