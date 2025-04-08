package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {

    Date orderDate;

    @NotBlank(message = "Место загрузки обязательно")
    String loadingPoint;

    @NotBlank(message = "Место доставки обязательно")
    String deliveryPoint;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    Long clientId;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    Long driverId;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    Long truckId;
}
