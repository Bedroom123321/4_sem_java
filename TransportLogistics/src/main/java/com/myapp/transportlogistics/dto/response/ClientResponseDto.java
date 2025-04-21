package com.myapp.transportlogistics.dto.response;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
