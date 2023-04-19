package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.model.*;
import com.example.java.test.junior.developer.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserQuizAnswersService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserPassedTestRepository userPassedTestRepository;
    private final UserQuizAnswersRepository userQuizAnswersRepository;


    public UserPassedTest initiateQuiz(Long userId, Long categoryId) {
        UserQuizAnswers userQuizAnswers = new UserQuizAnswers();

        User currentUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        UserPassedTest userPassedTest = new UserPassedTest();
        userPassedTest.setUser(currentUser);
        Optional<Category> category = categoryRepository.findById(categoryId);
        userPassedTest.setCategory(category.get());
        userPassedTest.setTimestamp(LocalDateTime.now());

        List<Question> questions = questionRepository.findByCategoryId(categoryId);
        userPassedTestRepository.save(userPassedTest);

        for (Question question : questions) {
            userQuizAnswers.setUserPassedTest(userPassedTest);
            userQuizAnswers.setQuestion(question);
            userQuizAnswers.setOption(null);
            userQuizAnswersRepository.save(userQuizAnswers);
        }

        return userPassedTest;
    }
}
