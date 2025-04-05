package com.myapp.transportlogistics.dto.request;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {
    Date orderDate;
    String loadingPoint;
    String deliveryPoint;
    Long clientId;
    Long driverId;
    Long truckId;
}
