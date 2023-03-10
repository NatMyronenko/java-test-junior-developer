package com.example.java.test.junior.developer.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private int id;

    @Column(name = "first_name",nullable = false)
    private String name;

    @Column(name = "last_name",nullable = false)
    private String surname;

    @Column(name = "email",nullable = false)
    private String email;

    public User() {

    }
}
