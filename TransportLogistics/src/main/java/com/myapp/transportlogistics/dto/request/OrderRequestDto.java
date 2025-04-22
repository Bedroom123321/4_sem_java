package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {

    private Date orderDate;

    @NotBlank(message = "Место загрузки обязательно")
    private String loadingPoint;

    @NotBlank(message = "Место доставки обязательно")
    private String deliveryPoint;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    private Long clientId;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    private Long driverId;

    @Pattern(regexp = "\\d+", message = "ID клиента должно быть числом больше 0")
    private Long truckId;

    public OrderRequestDto() {

    }

    public OrderRequestDto(Date orderDate, String loadingPoint, String deliveryPoint,
                           Long clientId, Long driverId, Long truckId) {
        this.orderDate = orderDate;
        this.loadingPoint = loadingPoint;
        this.deliveryPoint = deliveryPoint;
        this.clientId = clientId;
        this.driverId = driverId;
        this.truckId = truckId;
    }
}
