package com.example.java.test.junior.developer.rest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class POST implements Request {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    @Email(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
    String email;

    @NotBlank
    String password;
}
