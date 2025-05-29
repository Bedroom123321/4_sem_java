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
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String login;
    private String password;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Order> orders = new ArrayList<>();

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String phoneNumber, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password; // Пароль хранится как есть
    }

    @Override
    public String toString() {
        return "Client{"
                + "id=" + id
                + ", name='" + firstName + '\''
                + ", secondName='" + lastName + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", login='" + login + '\''
                + '}';
    }
}