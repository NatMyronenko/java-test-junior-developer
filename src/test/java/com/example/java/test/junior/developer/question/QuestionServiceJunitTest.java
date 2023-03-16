package com.example.java.test.junior.developer.question;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.mapper.QuestionMapper;
import com.example.java.test.junior.developer.model.Question;
import com.example.java.test.junior.developer.repository.QuestionRepository;
import com.example.java.test.junior.developer.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class QuestionServiceJunitTest {
    private final QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    private final QuestionMapper questionMapper = new QuestionMapper();
    private final QuestionService questionService = new QuestionService(questionRepository, questionMapper);

    @Test
    public void createQuestion_ValidDto_ReturnsDtoWithId() {
        QuestionDto questionDto = new QuestionDto(1, "What is polymorphism?");
        Question question = Question.builder()
                .id(1)
                .name(questionDto.getName())
                .build();

        when(questionRepository.save(any(Question.class))).thenReturn(question);

        QuestionDto result = questionService.createQuestion(questionDto);

        assertEquals(question.getId(), result.getId());
        assertEquals(question.getName(), result.getName());
    }

    @Test
    public void getAllQuestions_NoUsers_ReturnsEmptyList() {
        when(questionRepository.findAll()).thenReturn(Collections.emptyList());
        List<QuestionDto> result = questionService.getAllQuestions();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void updateQuestion_ValidIdAndDto_ReturnsDtoWithUpdatedFields() {
        int id = 1;
        QuestionDto questionDto = new QuestionDto(id, "What is polymorphism?");
        Question existingQuestion = Question.builder()
                .id(id)
                .name("What is polymorphism?")
                .build();
        Question updatedQuestion = Question.builder()
                .id(id)
                .name(questionDto.getName())
                .build();

        when(questionRepository.findById(id)).thenReturn(Optional.of(existingQuestion));
        when(questionRepository.save(any(Question.class))).thenReturn(updatedQuestion);

        QuestionDto result = questionService.updateQuestion(id, questionDto);

        assertEquals(id, result.getId());
        assertEquals(questionDto.getName(), result.getName());
    }

    @Test
    public void deleteQuestion_ValidId_DeletesUser() {
        int id = 1;
        questionService.deleteQuestion(id);
        Mockito.verify(questionRepository).deleteById(id);
    }
}


