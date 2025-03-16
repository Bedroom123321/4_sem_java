package com.myapp.transportlogistics.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String secondName;
    private String phoneNumber;
    private String workExperience;

    @ManyToMany(mappedBy = "drivers", fetch = FetchType.LAZY)
    private Set<Truck> trucks ;

    public Driver(Long id, String name, String secondName,
                  String phoneNumber, String workExperience, Set<Truck> trucks) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
        this.trucks = trucks;
    }

    public Driver() {
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", secondBName=" + secondName
                + ", phoneNumber=" + phoneNumber
                + ", workExperience='" + workExperience + '\''
                + '}';
    }
}
