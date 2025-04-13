package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.ClientRequestDto;
import com.myapp.transportlogistics.dto.response.ClientResponseDto;
import com.myapp.transportlogistics.exceprion.ValidationException;
import com.myapp.transportlogistics.service.impl.ClientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Tag(name = "Client Controller")
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Извлекает данные клиента по его ID",
               description = "Получате ID клиента, отправляет в сервис и возвращает DTO ответа"
    )
    @GetMapping("{id}")
    public ClientResponseDto getClientById(@PathVariable(value = "id")
                                               @Min(value = 1, message =
                                                       "ID должен быть больше 0") Long id) {
        return clientService.findById(id);
    }

    @Operation(summary = "Извлекает данные всех клиентов",
            description = "Возвращает список всех клиентов в базе в виде DTO ответа"
    )
    @GetMapping("all")
    public List<ClientResponseDto> getAllClients() {
        return clientService.findAllClients();
    }

    @Operation(summary = "Добавляет нового клиента",
            description = "Принимает DTO запроса с данными клиента, сохраняет в базу и "
                    + "возвращает DTO ответа добавленного клиента"
    )
    @PostMapping
    public ClientResponseDto addClient(@Valid @RequestBody ClientRequestDto clientRequestDto) {
        return clientService.addClient(clientRequestDto);
    }

    @Operation(summary = "Добавляет несколько новых клиентов",
            description = "Принимает список DTO запроса с данными клиентов, сохраняет в базу и "
                    + "возвращает список DTO ответа добавленных клиентов"
    )
    @PostMapping("bulk")
    public List<ClientResponseDto> addClients(@RequestBody
                                                  List<ClientRequestDto> clientRequestDtos) {
        return clientService.addClients(clientRequestDtos);
    }

    @Operation(summary = "Удаляет клиента по его ID",
            description = "Принимает ID клиента и удаляет соответствующую запись из базы данных"
    )
    @DeleteMapping("delete/{id}")
    public void deleteClient(@PathVariable("id") @Min(value = 1, message =
            "ID должен быть больше 0") Long id) {
        clientService.delete(id);
    }

    @Operation(summary = "Обновляет номер телефона клиента",
            description = "Принимает ID клиента и новый номер телефона, "
                    + "обновляет данные в базе данных"
    )
    @PutMapping("update/{id}")
    public void updateClientPhoneNumber(@PathVariable("id") @Min(value = 1, message =
                                                    "ID должен быть больше 0") Long id,
                                        @RequestParam String phoneNumber) {
        phoneNumberException(phoneNumber);

        clientService.update(id, phoneNumber);
    }

    private void phoneNumberException(String phoneNumber) {

        String pattern = "^\\+375(17|25|29|33|44)\\d{7}$";
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || !phoneNumber.matches(pattern)) {
            throw new ValidationException(
                    "Номер должен быть в формате +375XXXXXXXXX (после кода оператора 7 цифр)");
        }

    }

}
