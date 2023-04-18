package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final JdbcTemplate jdbcTemplate;

    @PostMapping("/initiate")
    public void initiateQuiz(@RequestParam Long userId, @RequestParam Long categoryId) {

        LocalDateTime timestamp = LocalDateTime.now();
        jdbcTemplate.update("INSERT INTO user_passed_test (user_id, category_id, timestamp) VALUES (?, ?, ?)",
                userId, categoryId, timestamp);

        List<Question> questions = jdbcTemplate.query(
                "SELECT * FROM questions WHERE category_id = ?",
                new Object[]{categoryId},
                (rs, rowNum) -> new Question(rs.getLong("id"), rs.getString("text"), categoryId)
        );

        Long quizId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        Object[][] params = new Object[questions.size()][3];
        for (int i = 0; i < questions.size(); i++) {
            params[i][0] = quizId;
            params[i][1] = questions.get(i);
            params[i][2] = null;
        }
        jdbcTemplate.batchUpdate("INSERT INTO user_quiz_answers (quiz_id, question_id, option_id) VALUES (?, ?, ?)",
                String.valueOf(params));
    }
}
