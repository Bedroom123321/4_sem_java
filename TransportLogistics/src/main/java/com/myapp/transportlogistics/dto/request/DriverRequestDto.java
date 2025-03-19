package com.myapp.transportlogistics.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class DriverRequestDto {

    @NotBlank(message = "Имя должно быть заполнено")
    private String name;

    @NotBlank(message = "Фамилия должна быть заполнена")
    private String secondName;

    @Pattern(regexp = "^\\\\+375\\\\d{9}$", message = "Неверный номер телефона")
    private String phoneNumber;

    @Pattern(regexp = "^\\d", message = "Стаж должен быть числом")
    private String workExperience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}
