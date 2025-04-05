package com.myapp.transportlogistics.dto.response;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderWithRelationsDto {
    Long id;
    Date orderDate;
    String loadingPoint;
    String deliveryPoint;
    ClientResponseDto client;
    DriverResponseDto driver;
    TruckResponseDto truck;
}
