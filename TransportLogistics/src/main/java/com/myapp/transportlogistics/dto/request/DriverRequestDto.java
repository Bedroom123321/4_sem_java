package com.myapp.transportlogistics.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverRequestDto {
    String firstName;
    String lastName;
    String phoneNumber;
    String workExperience;
}
