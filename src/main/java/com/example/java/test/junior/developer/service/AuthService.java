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
      log.info("Generating login response for email: {}", email);
      String accessToken = String.valueOf(keycloakAuthClient.getAccessToken(email, password));
      log.info("Access token generated successfully for email: {}", email);
      return new LoginResponseDto(null, null, accessToken, null, null, null);
    } catch (Exception ex) {
      log.error("Error occurred while generating login response for email: {}", email, ex);
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", ex);
    }
  }
}
