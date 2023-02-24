package com.example.java_test_junior_developer.model;

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

        private String languages;
        private String answer;

    public User() {
    }

    public User(int id, String name, String surname, int bals,String languages,String answer) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.bals = bals;
        this.languages = languages;
        this.answer = answer;
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

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bals=" + bals +
                '}';
    }
}
