package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.response.DriverResponseDto;
import com.myapp.transportlogistics.exception.ValidationException;
import com.myapp.transportlogistics.service.impl.DriverServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Tag(name = "Driver Controller")
@RestController
@RequestMapping("drivers")
public class DriverController {

    private final DriverServiceImpl driverServiceImpl;

    public DriverController(DriverServiceImpl driverService) {
        this.driverServiceImpl = driverService;
    }

    @Operation(summary = "Извлекает данные всех водителей",
            description = "Возвращает список всех водителей в базе в виде DTO ответа"
    )
    @GetMapping("all")
    public List<DriverResponseDto> getAllDrivers() {
        return driverServiceImpl.findAllDrivers();
    }

    @Operation(summary = "Извлекает данные водителя по его ID",
            description = "Получате ID водителя, отправляет в сервис и возвращает DTO ответа"
    )
    @GetMapping("{id}")
    public DriverResponseDto getDriverById(@PathVariable @Min(value = 1, message =
            "ID должен быть больше 0") Long id) {
        return driverServiceImpl.findById(id);
    }

    @Operation(summary = "Создаёт нового водителя",
            description = "Принимает DTO запроса с данными водителя, сохраняет в базу и "
                    + "возвращает DTO ответа созданного водителя"
    )
    @PostMapping
    public DriverResponseDto addDriver(@Valid @RequestBody DriverRequestDto driverRequestDto) {
        return driverServiceImpl.create(driverRequestDto);
    }

    @Operation(summary = "Добавляет несколько новых водителей",
            description = "Принимает список DTO запроса с данными водителей, сохраняет в базу и "
                    + "возвращает список DTO ответа добавленных водителей"
    )
    @PostMapping("bulk")
    public List<DriverResponseDto> addDrivers(@RequestBody
                                              List<DriverRequestDto> driverRequestDtos) {
        return driverServiceImpl.addDrivers(driverRequestDtos);
    }

    @Operation(summary = "Удаляет водителя по его ID",
            description = "Принимает ID водителя и удаляет соответствующую запись из базы данных"
    )
    @DeleteMapping("{id}")
    public void deleteDriver(@PathVariable @Min(value = 1, message =
            "ID должен быть больше 0") Long id) {
        driverServiceImpl.delete(id);
    }

    @Operation(summary = "Обновляет фамилию и номер телефона водителя",
            description = "Принимает ID водителя, а также данные, которые нужно"
                    + " изменить(фамилию и/или номер телефона), "
                    + "обновляет данные в базе данных"
    )
    @PutMapping("{id}")
    public DriverResponseDto updateDriver(@PathVariable Long id, @RequestBody DriverRequestDto driverRequestDto) {

        return driverServiceImpl.update(id, driverRequestDto);
    }

}
