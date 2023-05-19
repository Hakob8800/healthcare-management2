package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private UserType userType;
}
