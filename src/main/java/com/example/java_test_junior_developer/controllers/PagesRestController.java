package com.example.java_test_junior_developer.controllers;

import com.example.java_test_junior_developer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PagesRestController {
    @Autowired
    private UserService userService;


}
