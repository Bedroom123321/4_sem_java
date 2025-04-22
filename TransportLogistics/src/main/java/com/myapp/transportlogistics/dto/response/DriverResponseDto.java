package com.myapp.transportlogistics.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String workExperience;


    public DriverResponseDto() {

    }

    public DriverResponseDto(Long id, String firstName, String lastName,
                             String phoneNumber, String workExperience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
    }
}
