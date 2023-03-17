package com.example.java.test.junior.developer;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.mapper.QuestionMapper;
import com.example.java.test.junior.developer.model.Question;
import com.example.java.test.junior.developer.repository.QuestionRepository;
import com.example.java.test.junior.developer.service.QuestionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @Test
    public void testCreateQuestion() {
        // Создаем объекты DTO и Entity для тестирования
        QuestionDto questionDto = new QuestionDto(1, "What is polymorphism?");

        Question questionEntity = new Question();
        questionEntity.setId(1);
        questionEntity.setName("What is polymorphism?");

        // Указываем поведение мок-объектов

        when(questionMapper.toEntity(questionDto)).thenReturn(questionEntity);
        when(questionRepository.save(questionEntity)).thenReturn(questionEntity);
        when(questionMapper.toDto(questionEntity)).thenReturn(questionDto);

        // Вызываем тестируемый метод
        QuestionDto result = questionService.createQuestion(questionDto);

        // Проверяем, что методы мок-объектов были вызваны с нужными параметрами
        verify(questionMapper, times(1)).toEntity(questionDto);
        verify(questionRepository, times(1)).save(questionEntity);
        verify(questionMapper, times(1)).toDto(questionEntity);

        // Проверяем, что результат соответствует ожиданиям
        assertEquals(questionDto.getId(), result.getId());
        assertEquals(questionDto.getName(), result.getName());

    }

    @Test
    public void getAllQuestions_NoUsers_ReturnsEmptyList() {
        when(questionRepository.findAll()).thenReturn(Collections.emptyList());
        List<QuestionDto> result = questionService.getAllQuestions();
        assertEquals(Collections.emptyList(), result);
    }


    @Test
    public void testUpdateQuestion() {
        // Создаем объекты DTO и Entity для тестирования
        QuestionDto questionDto = new QuestionDto(1, "What is polymorphism?");

        Question questionEntity = new Question();
        questionEntity.setId(1);
        questionEntity.setName("What is polymorphism?");

        // Указываем поведение мок-объектов
        when(questionMapper.toEntity(questionDto)).thenReturn(questionEntity);
        when(questionRepository.save(questionEntity)).thenReturn(questionEntity);
        when(questionMapper.toDto(questionEntity)).thenReturn(questionDto);

        // Вызываем тестируемый метод
        QuestionDto result = questionService.updateQuestion(1, questionDto);

        // Проверяем, что методы мок-объектов были вызваны с нужными параметрами
        verify(questionMapper, times(1)).toEntity(questionDto);
        verify(questionRepository, times(1)).save(questionEntity);
        verify(questionMapper, times(1)).toDto(questionEntity);

        // Проверяем, что результат соответствует ожиданиям
        assertEquals(questionDto.getId(), result.getId());
        assertEquals(questionDto.getName(), result.getName());
    }

    @Test
    public void deleteQuestion_ValidId_DeletesUser() {
        int id = 1;
        questionService.deleteQuestion(id);
        Mockito.verify(questionRepository).deleteById(id);
    }
}

