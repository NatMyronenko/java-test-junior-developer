package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class UserController {
    ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();

    @CrossOrigin
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @CrossOrigin
    @GetMapping("/")
    public List<User> getAllUsers() {
        return new ArrayList<User>(users.values());
    }

    @CrossOrigin
    @PostMapping("/")
    public User getUsers(@RequestBody User user) {
        users.put(user.getId(), user);
        return user;
    }
}



