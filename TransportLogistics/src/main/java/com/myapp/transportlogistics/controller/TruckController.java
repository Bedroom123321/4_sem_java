package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.DriverRequestDto;
import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import com.myapp.transportlogistics.exceprion.ValidationException;
import com.myapp.transportlogistics.service.impl.TruckServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Truck Controller")
@RestController
@RequestMapping("trucks")
public class TruckController {

    private final TruckServiceImpl truckServiceImpl;

    public TruckController(TruckServiceImpl truckServiceImpl) {
        this.truckServiceImpl = truckServiceImpl;
    }

    @Operation(summary = "Извлекает данные всеx транспортных средств",
            description = "Возвращает список всех транспортных средств в базе в виде DTO ответа"
    )
    @GetMapping("get/all")
    public List<TruckResponseDto> getTrucks() {
        return truckServiceImpl.findAllTrucks();
    }

    @Operation(summary = "Извлекает данные транспорта по его ID",
            description = "Получате ID транспорта, отправляет в сервис и возвращает DTO ответа"
    )
    @GetMapping("get/{id}")
    public TruckResponseDto getTruckById(@PathVariable Long id) {
        idException(id);
        return truckServiceImpl.findById(id);
    }

    @Operation(summary = "Извлекает данные всех транспортных средств, "
                       + "которые использовал конкретный водитель",
            description = "Получате ID водителя и возвращает список всех транспортных средств"
                        + " в базе, которые использовал данный водитель, в виде DTO ответа"
    )
    @GetMapping("get/by-driver/{driverId}")
    public List<TruckResponseDto> getTrucksByDriverId(@PathVariable Long driverId) {
        idException(driverId);
        return truckServiceImpl.getTrucksByDriverId(driverId);
    }

    @Operation(summary = "Создаёт новый транспорт",
            description = "Принимает DTO запроса с данными транспорта, сохраняет в базу и "
                    + "возвращает DTO ответа созданного транспорта"
    )
    @PostMapping("post")
    public TruckResponseDto creatTruck(@Valid @RequestBody TruckRequestDto truckRequestDto) {
        return truckServiceImpl.create(truckRequestDto);
    }

    @Operation(summary = "Удаляет транспорт по его ID",
            description = "Принимает ID транспорта и удаляет соответствующую запись из базы данных"
    )
    @DeleteMapping("delete/{id}")
    public void deleteTruck(@PathVariable Long id) {
        idException(id);
        truckServiceImpl.delete(id);
    }

    @Operation(summary = "Обновляет тип груза и объём груза",
            description = "Принимает ID транспорта, а также данные, которые нужно"
                    + " изменить(тип и/или объём груза), обновляет данные в базе данных"
    )
    @PutMapping("update/{id}")
    public void updateTruck(@PathVariable Long id, @RequestParam(required = false) String cargoType,
                            @RequestParam(required = false) Integer cargoVolume) {
        idException(id);
        cargoTypeException(cargoType);
        cargoVolumeException(cargoVolume);

        truckServiceImpl.update(id, cargoType, cargoVolume);
    }

    private void idException(Long id) {

        if (id == null || id <= 0) {
            throw new ValidationException("ID должен быть больше нуля");
        }

    }

    private void cargoTypeException(String cargoType)throws ValidationException {

        if (cargoType == null || cargoType.isEmpty()) {
            throw new ValidationException("Тип превозимого груза обязателен");
        }

    }

    private void cargoVolumeException(Integer cargoVolume) {

        if (cargoVolume == null || cargoVolume <= 0) {
            throw new ValidationException("Объем груза должен быть больше нуля");
        }

    }
}
