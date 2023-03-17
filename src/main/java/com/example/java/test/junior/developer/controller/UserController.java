package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.dto.UserRequestDto;
import com.example.java.test.junior.developer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserRequestDto userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@Valid @PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @RequestBody @Valid UserDto user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }
}
