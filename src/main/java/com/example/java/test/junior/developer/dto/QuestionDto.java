package com.example.java.test.junior.developer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class QuestionDto {

    int id;

    @NotBlank String name;

}


