package com.example.java.test.junior.developer.controllers;

import com.example.java.test.junior.developer.models.User;
import com.example.java.test.junior.developer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/java-test")
public class UserController {
        ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();

        @GetMapping("/{id}")
        public User getUser(@PathVariable Long id) {
            return users.get(id);
        }

        @GetMapping("/")
        public List<User> getAllUsers() {
            return new ArrayList<User>(users.values());
        }

        @PostMapping("/")
        public User getUsers(@RequestBody User user) {
            users.put(user.getId(), user);
            return user;
        }
    }

