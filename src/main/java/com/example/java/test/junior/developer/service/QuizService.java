package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.model.*;
import com.example.java.test.junior.developer.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    private final UserPassedTestRepository userPassedTestRepository;

    private final UserQuizAnswersRepository userQuizAnswersRepository;


    public UserPassedTest initiateQuiz(Long userId, Long categoryId) {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        UserPassedTest userPassedTest = new UserPassedTest();
        userPassedTest.setUser(currentUser);
        userPassedTest.setTimestamp(LocalDateTime.now());
        userPassedTestRepository.save(userPassedTest);

        Quiz quiz = new Quiz();
        quiz.setUser(currentUser);

        List<Question> questions = questionRepository.findByCategoryId(categoryId);

        for (Question question : questions) {
            UserQuizAnswers userQuizAnswers = new UserQuizAnswers();
            userQuizAnswers.setQuiz(quiz);
            userQuizAnswers.setQuestion(question);
            userQuizAnswers.setOption(null); // Initially all answers are null
            userQuizAnswersRepository.save(userQuizAnswers);
        }

        return userPassedTest;
    }
}
