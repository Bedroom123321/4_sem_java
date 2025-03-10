package com.myapp.transportlogistics.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    private int id;
    private String name;
    private int secondName;
    private int phoneNumber;
    private String workExperience;

    public Driver() {
    }

    public Driver(int id, String workExperience, int phoneNumber, int secondName, String name) {
        this.id = id;
        this.workExperience = workExperience;
        this.phoneNumber = phoneNumber;
        this.secondName = secondName;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSecondName() {
        return secondName;
    }

    public void setSecondName(int secondName) {
        this.secondName = secondName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}
