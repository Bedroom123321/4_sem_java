package com.myapp.transportlogistics.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientRequestDto {
    String firstName;
    String lastName;
    String phoneNumber;
}
