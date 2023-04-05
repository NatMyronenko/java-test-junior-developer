package com.example.java.test.junior.developer.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.java.test.junior.developer.dto.LoginRequestDto;
import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(AuthController.class)
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthService authService;

  @Test
  void generateToken_withValidLoginRequest_returnsLoginResponseDto() throws Exception {
    LoginRequestDto requestDto = LoginRequestDto.builder()
        .email("test@example.com")
        .password("Password123!")
        .build();
    LoginResponseDto responseDto = new LoginResponseDto("access_token");

    given(authService.generateLoginResponse(requestDto.getEmail(), requestDto.getPassword()))
        .willReturn(responseDto);

    mockMvc.perform(post("/api/v1/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(requestDto)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.access_token").value(responseDto.getAccessToken()));
  }
}

