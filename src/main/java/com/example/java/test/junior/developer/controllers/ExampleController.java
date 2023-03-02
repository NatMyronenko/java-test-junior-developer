package com.example.java.test.junior.developer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExampleController {

    @GetMapping("/example")
    public ResponseEntity<Object> getExample() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "This is an example response.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}