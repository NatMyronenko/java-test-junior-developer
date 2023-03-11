package com.example.java.test.junior.developer.dto;

import io.smallrye.common.constraint.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryDto {

    int id;

    @NotBlank String category_name;

  //  @NotNull  Long id;

}
