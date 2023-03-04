package com.example.java.test.junior.developer;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
//
//    @Column(name = "surname")
//    private String surname;
//
//    @Column(name = "totalBals")
//    private int totalBals;
//




    public User() {

    }
}
