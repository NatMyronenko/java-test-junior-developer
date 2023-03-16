package com.example.java.test.junior.developer.service;


import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.mapper.QuestionMapper;
import com.example.java.test.junior.developer.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    @Transactional
    public QuestionDto createQuestion(QuestionDto dto) {
        final var question = questionMapper.toEntity(dto);
        final var saved = questionRepository.save(question);
        return questionMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<QuestionDto> getAllQuestions() {
        final var questions = questionRepository.findAll();
        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public QuestionDto updateQuestion(int id, QuestionDto dto) {
        final var question = questionMapper.toEntity(dto);
        question.setId(id);
        final var saved = questionRepository.save(question);
        return questionMapper.toDto(saved);
    }

    @Transactional
    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

}

