package com.myapp.transportlogistics.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
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

    @OneToMany(mappedBy = "driver", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Order> orders;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id", unique = true)
    private Truck truck;
    
    public Driver() {
    }

    public Driver(String name, String secondName,
                  String phoneNumber, String workExperience) {
        this.name = name;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
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
