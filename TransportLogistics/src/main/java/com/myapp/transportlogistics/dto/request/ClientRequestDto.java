package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientRequestDto {

    @NotBlank(message = "Имя клиента обязательно")
    @Size(min = 2, max = 20, message = "Длина имени должна быть 2-20 символов")
    String firstName;

    @NotBlank(message = "Фамилия клиента обязательна")
    @Size(min = 2, max = 20, message = "Длина фамилии должна быть 2-20 символов")
    String lastName;

    @NotBlank(message = "Номер телефона клиента обязателен")
    @Pattern(regexp = "^\\+375(17|25|29|33|44)\\d{7}$",
            message = "Номер должен быть в формате +375XXXXXXXXX")
    String phoneNumber;
}
