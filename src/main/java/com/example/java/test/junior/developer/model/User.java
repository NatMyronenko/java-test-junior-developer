package com.example.java.test.junior.developer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
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

    @Column(name = "email",nullable = false)
    private String password;

}
