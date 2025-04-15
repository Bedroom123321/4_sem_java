package com.myapp.transportlogistics.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public ClientResponseDto() {
    }
}
