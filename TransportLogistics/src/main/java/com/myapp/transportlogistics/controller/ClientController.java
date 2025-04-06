package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.service.impl.ClientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
@Tag(name = "Client Controller")
@RestController
@RequestMapping("clients")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ClientController {

    ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Извлекает данные клиента по его ID",
               description = "Получате ID клиента, отправляет в сервис и возвращает DTO ответа"
    )
    @GetMapping("get/{id}")
    public ClientResponseDto getClientById(@PathVariable(value = "id")
                                           @Positive(message = "ID должен быть больше нуля")
                                           Long id) {
        return clientService.findById(id);
    }

    @Operation(summary = "Извлекает данные всех клиентов",
            description = "Возвращает список всех клиентов в базе в виде DTO ответа"
    )
    @GetMapping("get/all")
    public List<ClientResponseDto> getAllClients() {
        return clientService.findAllClients();
    }

    @Operation(summary = "Создаёт нового клиента",
            description = "Принимает DTO запроса с данными клиента, сохраняет в базу и "
                    + "возвращает DTO ответа созданного клиента"
    )
    @PostMapping("post")
    public ClientResponseDto createClient(@Valid @RequestBody ClientRequestDto clientRequestDto) {
        return clientService.create(clientRequestDto);
    }

    @Operation(summary = "Удаляет клиента по его ID",
            description = "Принимает ID клиента и удаляет соответствующую запись из базы данных"
    )
    @DeleteMapping("delete/{id}")
    public void deleteClient(@PathVariable("id") @Positive
            (message = "ID должен быть больше нуля") Long id) {
        clientService.delete(id);
    }

    @Operation(summary = "Обновляет номер телефона клиента",
            description = "Принимает ID клиента и новый номер телефона, "
                    + "обновляет данные в базе данных"
    )
    @PutMapping("update/{id}")
    public void updateClientPhoneNumber(@PathVariable("id") @Positive
                                        (message = "ID должен быть больше нуля") Long id,
                                        @NotBlank(message = "Номер телефона обязателен")
                                        @RequestParam @Pattern
                                        (regexp = "^\\+375(17|25|29|33|44)\\d{7}$",
                                        message = "Номер должен быть в формате +375XXXXXXXXX")
                                        String phoneNumber) {
        clientService.update(id, phoneNumber);
    }
}
