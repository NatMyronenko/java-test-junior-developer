package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//TODO: перероблю під DTO і mapper завтра
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@Valid @PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                                      @RequestBody @Valid User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
