package com.example.java.test.junior.developer.configuration;

import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class KeycloakAdminClient {

    private final UserService userService;

    public KeycloakAdminClient(UserService userService) {
        this.userService = userService;
    }

    private List <User> users = new ArrayList<>();

    //public User createUser (@RequestBody User user) {
    public List<User> createUser (@RequestBody User user) {

        userService.saveUser(user);
        users.add(user);

        //User newUser = new User();
        //Storing the incoming data in the list
        //User.Data.add(new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEMail()));

        //return String.format ("%s is creates.", user.toString());
        return users;
        //return new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEMail());
    }
}