package com.example.java.test.junior.developer.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.java.test.junior.developer.dto.LogOutRequestDto;
import com.example.java.test.junior.developer.security.SecurityConfig;
import com.example.java.test.junior.developer.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LogOutController.class)
@WithMockUser
@Import(SecurityConfig.class)
@ActiveProfiles("test")
class LogOutControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthService authService;

  @Test
  void logout_withValidLogOutRequest_returnsNoContent() throws Exception {
    LogOutRequestDto requestDto = LogOutRequestDto.builder()
        .accessToken("access_token")
        .refreshToken("refresh_token")
        .build();

    mockMvc.perform(post("/api/v1/logout")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(requestDto)))
        .andExpect(status().isOk());

    verify(authService, times(1)).logout(requestDto);
  }
}