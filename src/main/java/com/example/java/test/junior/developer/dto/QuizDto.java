package com.example.java.test.junior.developer.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class QuizDto {
    Long quiz_id;
    Long question_id;
    Long option_id;
    Long user_id;
    Long category_id;
}
