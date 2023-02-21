package com.example.java_test_junior_developer.controller;

import com.example.java_test_junior_developer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;
}
