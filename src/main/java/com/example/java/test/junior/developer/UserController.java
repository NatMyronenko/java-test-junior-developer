package com.example.java.test.junior.developer;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class UserController {

    //відповідає за обробку даних, які вносить юзер

    ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return new ArrayList<User>(users.values());
    }

    @PostMapping("/new_user")
    public User createUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return user;
    }
}



