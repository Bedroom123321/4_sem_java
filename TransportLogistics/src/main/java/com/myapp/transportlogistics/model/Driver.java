package com.myapp.transportlogistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.Set;


@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "secondname")
    private String secondName;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "workexperience")
    private String workExperience;

    @ManyToMany(mappedBy = "drivers")
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
