package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

    public ClientRequestDto() {

    }

    public ClientRequestDto(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
