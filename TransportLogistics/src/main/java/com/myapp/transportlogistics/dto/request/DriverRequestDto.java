package com.myapp.transportlogistics.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String workExperience;
}
