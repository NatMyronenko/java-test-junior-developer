package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/initiate")
    public void initiateQuiz(@RequestParam Long userId, @RequestParam Long categoryId) {
       quizService.initiateQuiz(userId, categoryId);
    }
}
