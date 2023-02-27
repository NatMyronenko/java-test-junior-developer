package com.example.java_test_junior_developer.controllers;

import ch.qos.logback.core.model.Model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PagesController {



    @GetMapping("/1_question")
    public String tesQuestion1(Model model) {
        return "1_question";
    }

    @GetMapping("/2_question")
    public String testQuestion2(Model model) {
        return "2_question";
    }


}
