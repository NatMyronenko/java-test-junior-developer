package com.example.java_test_junior_developer.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private int id;

        @Column(name = "name")
        private String name;

        @Column(name = "surname")
        private String surname;

        @Column(name = "bals")
        private int bals;

    public User() {
    }

    public User(int id, String name, String surname, int bals) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.bals = bals;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBals() {
        return bals;
    }

    public void setBals(int bals) {
        this.bals = bals;
    }
}
