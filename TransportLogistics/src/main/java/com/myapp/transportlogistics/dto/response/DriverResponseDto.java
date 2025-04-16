package com.myapp.transportlogistics.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverResponseDto {
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    String workExperience;


    public DriverResponseDto(Long id, String firstName, String lastName,
                             String phoneNumber, String workExperience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
    }

    public DriverResponseDto() {

    }
}
