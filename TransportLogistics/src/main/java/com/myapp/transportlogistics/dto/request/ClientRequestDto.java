package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDto {

    @NotBlank(message = "Имя клиента обязательно")
    @Size(min = 2, max = 20, message = "Имя должно содержать от 2 до 20 символов")
    private String firstName;

    @NotBlank(message = "Фамилия клиента обязательна")
    @Size(min = 2, max = 20, message = "Фамилия должна содержать от 2 до 20 символов")
    private String lastName;

    @Pattern(regexp = "^\\+375(17|25|29|33|44)\\d{7}$", message =
            "Номер должен быть в формате +375XXXXXXXXX (после кода оператора 7 цифр)")
    private String phoneNumber;

    @NotBlank(message = "Логин обязателен")
    @Size(min = 4, max = 20, message = "Логин должен содержать от 4 до 20 символов")
    private String login;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;

    public ClientRequestDto() {
    }

    public ClientRequestDto(String firstName, String lastName, String phoneNumber, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }
}