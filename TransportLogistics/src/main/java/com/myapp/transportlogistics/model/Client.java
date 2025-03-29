package com.myapp.transportlogistics.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String secondName;
    private String phoneNumber;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REMOVE})
    private List<Order> orders = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String secondName, String phoneNumber) {
        this.name = name;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", secondName='" + secondName + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + '}';
    }
}

