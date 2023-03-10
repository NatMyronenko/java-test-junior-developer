package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.LanguageDto;
import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.service.LanguageService;
import com.example.java.test.junior.developer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public UserDto createUser(@RequestBody @Valid UserDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/get/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable int id,
                                      @RequestBody @Valid UserDto user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}







