package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.service.UserQuizAnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class UserQuizAnswersController {

    private final UserQuizAnswersService userQuizAnswersService;

    @PostMapping("/initiate")
    public void initiateQuiz(@RequestParam Long userId, @RequestParam Long categoryId) {
       userQuizAnswersService.initiateQuiz(userId, categoryId);
    }
}
