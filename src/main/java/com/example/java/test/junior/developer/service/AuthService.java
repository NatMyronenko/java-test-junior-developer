package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.security.KeycloakAuthClient;
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
      String accessToken = String.valueOf(keycloakAuthClient.getAccessToken(email, password));
      return new LoginResponseDto(null, null, accessToken, null, null, null);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", ex);
    }
  }
}

