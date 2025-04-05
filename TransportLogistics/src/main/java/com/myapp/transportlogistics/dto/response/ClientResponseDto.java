package com.myapp.transportlogistics.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientResponseDto {
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
}
