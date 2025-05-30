package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import com.myapp.transportlogistics.exception.EntityAlreadyExistsException;
import com.myapp.transportlogistics.exception.EntityNotFoundException;
import com.myapp.transportlogistics.exception.ValidationException;
import com.myapp.transportlogistics.service.impl.TruckServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Operation(summary = "Извлекает данные всех транспортных средств",
            description = "Возвращает список всех транспортных средств в базе в виде DTO ответа")
    @GetMapping("all")
    public List<TruckResponseDto> getTrucks() {
        return truckServiceImpl.findAllTrucks();
    }

    @Operation(summary = "Извлекает данные транспорта по его ID",
            description = "Получает ID транспорта, отправляет в сервис и возвращает DTO ответа")
    @GetMapping("{id}")
    public TruckResponseDto getTruckById(@PathVariable @Min(value = 1, message =
            "ID должен быть больше 0") Long id) {
        return truckServiceImpl.findById(id);
    }

    @Operation(summary = "Извлекает данные всех транспортных средств, "
            + "которые использовал конкретный водитель",
            description = "Получает ID водителя и возвращает список всех транспортных средств"
                    + " в базе, которые использовал данный водитель, в виде DTO ответа")
    @GetMapping("by-driver/{driverId}")
    public List<TruckResponseDto> getTrucksByDriverId(@PathVariable
                                                      @Min(value = 1, message =
                                                              "ID должен быть больше 0")
                                                      Long driverId) {
        return truckServiceImpl.getTrucksByDriverId(driverId);
    }

    @Operation(summary = "Создаёт новый транспорт",
            description = "Принимает DTO запроса с данными транспорта, сохраняет в базу и "
                    + "возвращает DTO ответа созданного транспорта")
    @PostMapping
    public TruckResponseDto addTruck(@Valid @RequestBody TruckRequestDto truckRequestDto) {
        return truckServiceImpl.addTruck(truckRequestDto);
    }

    @Operation(summary = "Добавляет несколько новых транспортных средств",
            description = "Принимает список DTO запроса с данными "
                    + "транспортных средств, сохраняет в базу и "
                    + "возвращает список DTO ответа добавленных транспортных средств")
    @PostMapping("bulk")
    public List<TruckResponseDto> addTrucks(@RequestBody
                                            List<TruckRequestDto> truckRequestDtos) {
        return truckServiceImpl.addTrucks(truckRequestDtos);
    }

    @Operation(summary = "Удаляет транспорт по его ID",
            description = "Принимает ID транспорта и удаляет соответствующую запись из базы данных")
    @DeleteMapping("{id}")
    public void deleteTruck(@PathVariable @Min(value = 1, message =
            "ID должен быть больше 0") Long id) {
        truckServiceImpl.delete(id);
    }

    @Operation(summary = "Обновляет данные транспорта",
            description = "Принимает ID транспорта и DTO с новыми данными, обновляет запись в базе и возвращает обновлённый DTO")
    @PutMapping("{id}")
    public ResponseEntity<?> updateTruck(@PathVariable @Min(value = 1, message =
                                                 "ID должен быть больше 0") Long id,
                                         @Valid @RequestBody TruckRequestDto truckRequestDto) {
        try {
            TruckResponseDto updatedTruck = truckServiceImpl.update(id, truckRequestDto);
            return ResponseEntity.ok(updatedTruck);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (EntityAlreadyExistsException e) {
            return ResponseEntity.status(409).body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Внутренняя ошибка сервера\"}");
        }
    }
}