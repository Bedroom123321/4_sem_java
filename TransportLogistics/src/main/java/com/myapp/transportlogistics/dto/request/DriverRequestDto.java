package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DriverRequestDto {

    @NotBlank(message = "Имя клиента обязательно")
    @Size(min = 2, max = 20, message = "Имя должно содержать от 2 до 20 символов")
    private String firstName;

    @NotBlank(message = "Фамилия клиента обязательна")
    @Size(min = 2, max = 20, message = "Фамилия должна содержать от 2 до 20 символов")
    private String lastName;

    @Pattern(regexp = "^\\+375(17|25|29|33|44)\\d{7}$", message =
            "Номер должен быть в формате +375XXXXXXXXX (после кода оператора 7 цифр)")
    private String phoneNumber;

    @Positive(message = "Стаж должен быть корректным(больше или равно 0)")
    private String workExperience;


    public DriverRequestDto(String firstName, String lastName,
                            String phoneNumber, String workExperience) {
        this.firstName = firstName;
        this.workExperience = workExperience;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
    }

    public DriverRequestDto() {

    }
}
