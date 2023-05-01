package com.example.java.test.junior.developer.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.java.test.junior.developer.dto.LoginRequestDto;
import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.security.SecurityConfig;
import com.example.java.test.junior.developer.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
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
    LoginResponseDto responseDto = LoginResponseDto.builder()
        .accessToken("access_token")
        .sessionState("session_state")
        .tokenType("Bearer")
        .expiresIn(3600L)
        .refreshToken("refresh_token")
        .refreshExpiresIn(3600L)
        .build();

    Mockito.when(authService.generateLoginResponse(requestDto.getEmail(), requestDto.getPassword()))
        .thenReturn(responseDto);

    mockMvc.perform(post("/api/v1/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(requestDto))
            .header(HttpHeaders.AUTHORIZATION, "Bearer access_token")
            .with(csrf().asHeader()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.session_state").value(responseDto.getSessionState()))
        .andExpect(jsonPath("$.token_type").value(responseDto.getTokenType()))
        .andExpect(jsonPath("$.access_token").value(responseDto.getAccessToken()))
        .andExpect(jsonPath("$.expires_in").value(responseDto.getExpiresIn()))
        .andExpect(jsonPath("$.refresh_token").value(responseDto.getRefreshToken()))
        .andExpect(jsonPath("$.refresh_expires_in").value(responseDto.getRefreshExpiresIn()));
  }
}