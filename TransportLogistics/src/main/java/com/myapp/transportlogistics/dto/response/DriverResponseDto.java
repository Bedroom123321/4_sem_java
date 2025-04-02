package com.myapp.transportlogistics.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String workExperience;
}
