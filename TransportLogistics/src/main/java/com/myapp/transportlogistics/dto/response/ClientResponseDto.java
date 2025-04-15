package com.myapp.transportlogistics.dto.response;

import lombok.Data;

@Data
public class ClientResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public ClientResponseDto() {
    }

    public ClientResponseDto(Long id, String firstName, String lastName, String phoneNumber) {
        this.lastName = lastName;
        this.id = id;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }
}
