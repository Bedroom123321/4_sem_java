package com.myapp.transportlogistics.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String login;
    private String password; // Добавлено поле для пароля

    public ClientResponseDto() {
    }

    public ClientResponseDto(Long id, String firstName, String lastName, String phoneNumber, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password; // Добавлен параметр в конструктор
    }
}