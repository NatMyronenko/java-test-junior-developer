package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

        private final UserService userService;

       @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User user) {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }

        @GetMapping("/get/{id}")
        public ResponseEntity <User> getUserById(@PathVariable int id) {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }

        @GetMapping
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable int id) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
    }







