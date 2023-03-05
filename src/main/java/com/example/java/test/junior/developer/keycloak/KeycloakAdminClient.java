package com.example.java.test.junior.developer.keycloak;

import com.example.java.test.junior.developer.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class KeycloakAdminClient {

    //public User createUser (@RequestBody User user) {
    public List<User> createUser (@RequestBody User user) {

        //User newUser = new User();
        // Storing the incoming data in the list
        User.Data.add(new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEMail()));

        //return String.format ("%s is creates.", user.toString());
        return User.Data;
        //return new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEMail());
    }
}
