package com.myapp.transportlogistics.controller;

import com.myapp.transportlogistics.dto.request.OrderRequestDto;
import com.myapp.transportlogistics.dto.request.TruckRequestDto;
import com.myapp.transportlogistics.dto.response.OrderResponseDto;
import com.myapp.transportlogistics.dto.response.OrderWithRelationsDto;
import com.myapp.transportlogistics.dto.response.TruckResponseDto;
import com.myapp.transportlogistics.exceprion.ValidationException;
import com.myapp.transportlogistics.service.impl.OrderServiceImpl;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Tag(name = "Order Controller")
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @Operation(summary = "Извлекает данные всех заказов",
            description = "Возвращает список всех заказов в базе в виде DTO ответа"
    )
    @GetMapping("all")
    public List<OrderResponseDto> getAllOrders() {
        return orderServiceImpl.getAllOrders();
    }

    @Operation(summary = "Извлекает данные всех заказов со всеми связанными сущностями",
            description = "Возвращает список всех заказов в базе с данными "
                        + "клиентов, водитлей и транспорта в виде DTO ответа"
    )
    @GetMapping("all-with_relations")
    public List<OrderWithRelationsDto> getAllOrdersWithRelations() {
        return orderServiceImpl.getAllWithRelations();
    }

    @Operation(summary = "Извлекает данные заказа по его ID",
            description = "Получате ID заказа, отправляет в сервис и возвращает DTO ответа"
    )
    @GetMapping("{id}")
    public OrderResponseDto getOrderById(@PathVariable @Min(value = 1, message =
            "ID должен быть больше 0") Long id) {
        return orderServiceImpl.getOrderById(id);
    }

    @Operation(summary = "Извлекает данные заказов по ID клиента",
            description = "Получате ID клиента, отправляет в сервис и возвращает"
                        + " список всех заказов клиента в виде DTO ответа"
    )
    @GetMapping("by-client")
    public List<OrderResponseDto> getOrderByClientPhoneNumber(@RequestParam String phoneNumber) {
        phoneNumberException(phoneNumber);
        return orderServiceImpl.getOrderByClientPhoneNumber(phoneNumber);
    }

    @Operation(summary = "Извлекает данные заказов по ID водителя",
            description = "Получате ID водителя, отправляет в сервис и возвращает"
                        + " список всех заказов, выполненых водителем, в виде DTO ответа"
    )
    @GetMapping("by-driver")
    public List<OrderResponseDto> getOrderByDriverId(@RequestParam String firstName,
                                                     @RequestParam String lastName) {
        driverException(firstName, lastName);
        return orderServiceImpl.getOrderByDriver(firstName, lastName);
    }

    @Operation(summary = "Создаёт новый заказ",
            description = "Принимает DTO запроса с данными заказа, сохраняет в базу и "
                        + "возвращает DTO ответа созданного заказа"
    )
    @PostMapping("post")
    public OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return orderServiceImpl.createOrder(orderRequestDto);
    }

    @Operation(summary = "Удаляет заказ по его ID",
            description = "Принимает ID заказа и удаляет соответствующую запись из базы данных"
    )
    @DeleteMapping("delete/{id}")
    public void deleteOrder(@PathVariable @Min(value = 1, message =
            "ID должен быть больше 0") Long id) {
        orderServiceImpl.delete(id);
    }

    private void phoneNumberException(String phoneNumber)throws ValidationException {

        String pattern = "^\\+375(17|25|29|33|44)\\d{7}$";
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || !phoneNumber.matches(pattern)) {
            throw new ValidationException(
                    "Номер должен быть в формате +375XXXXXXXXX (после кода оператора 7 цифр)");
        }

    }

    private void driverException(String firstName, String lastName) {

        if (firstName == null || firstName.isEmpty()) {
            throw new ValidationException("Имя водителя обязательно");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new ValidationException("Фамилия водителя обязательна");
        }
    }

}

