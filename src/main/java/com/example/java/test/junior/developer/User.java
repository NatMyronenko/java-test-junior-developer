package com.example.java.test.junior.developer;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public static List<User> Data = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "eMail")
    @Email(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
    private String eMail;
/*
    public User(long id, String firstName, String lastName, String eMail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }
*/
/*
    @Override
    public String toString() {
        return String.format("User{" +
                "id=%d, " +
                "firstName='%s', " +
                "lastName='%s', " +
                "eMail='%s'" +
                '}', id, firstName, lastName, eMail);
    }
*/

}
