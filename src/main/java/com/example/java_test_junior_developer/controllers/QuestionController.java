package com.example.java_test_junior_developer.controllers;

import com.example.java_test_junior_developer.models.Question;
import com.example.java_test_junior_developer.models.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/")
    public String questions(Model model){
        model.addAttribute("questions",questionService.listQuestions());
        return "list-questions";
    }
    @PostMapping("/question/create")
    public String createQuestion(Question question){
        questionService.saveQuestion(question);
        return "redirect:/";
    }
    @GetMapping("/question/{id}")
    public String questionInfo(@PathVariable Long id,Model model){
        model.addAttribute("question",questionService.getQuestionById(id));
        return "question-info";
    }
    @PostMapping("/question/delete/{id}")
    public String deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return "redirect:/";

    }


}
