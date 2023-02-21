package com.example.java_test_junior_developer.controller;

import com.example.java_test_junior_developer.entity.User;
import com.example.java_test_junior_developer.repository.UserRepository;
import com.example.java_test_junior_developer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> showAllUsers(){
        List<User> users = userService.getAllUsers();
        return users;
    }
    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "Employee with ID =" + id + "  was deleted";
    }
}
