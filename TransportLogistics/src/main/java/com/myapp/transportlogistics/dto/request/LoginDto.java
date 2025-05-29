package com.myapp.transportlogistics.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String login;
    private String password;


    public LoginDto() {
    }

    public LoginDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
