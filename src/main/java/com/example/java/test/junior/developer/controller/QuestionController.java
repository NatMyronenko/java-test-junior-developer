package com.example.java.test.junior.developer.controller;
import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.model.Question;
import com.example.java.test.junior.developer.service.CategoryService;
import com.example.java.test.junior.developer.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public QuestionDto createQuestion(@RequestBody @Valid QuestionDto question){
        return questionService.createQuestion(question);
    }

    @GetMapping
    public List<QuestionDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PutMapping("/{id}")
    public QuestionDto updateQuestion(@PathVariable int id,
                                      @RequestBody @Valid QuestionDto question){
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
    }
}
