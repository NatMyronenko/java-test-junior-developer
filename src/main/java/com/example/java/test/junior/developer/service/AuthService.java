package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.security.KeycloakAuthClient;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

  private final KeycloakAuthClient keycloakAuthClient;

  public LoginResponseDto generateLoginResponse(String email, String password) {
    try {
      String accessToken = keycloakAuthClient.getAccessToken(email, password);
      LoginResponseDto response = LoginResponseDto.builder()
          .sessionState(UUID.randomUUID().toString())
          .tokenType("Bearer")
          .accessToken(accessToken)
          .expiresIn(TimeUnit.MINUTES.toSeconds(30))
          .refreshToken(UUID.randomUUID().toString())
          .refreshExpiresIn(TimeUnit.DAYS.toSeconds(30))
          .build();
      return response;
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", ex);
    }
  }
}