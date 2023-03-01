package com.example.java.test.junior.developer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "answer")
    private boolean answer1;

    @Column(name = "answer2")
    private boolean answer2;

    @Column(name = "answer3")
    private boolean answer3;

    @Column(name = "bal")
    private int bal;


    public Question() {

    }
}