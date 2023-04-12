package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.security.KeycloakAuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

  private final KeycloakAuthClient keycloakAuthClient;

  public LoginResponseDto generateLoginResponse(String email, String password) {
    try {
      log.info("Generating login response for email: {}", email);
      LoginResponseDto responseDto = keycloakAuthClient.getAccessToken(email, password);
      log.info("Access token generated successfully for email: {}", email);
      return responseDto;
    } catch (Exception ex) {
      log.error("Error occurred while generating login response for email: {}", email, ex);
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", ex);
    }

  }

  public String getAccessTokenFromHeader(String authorizationHeader) {
    String token = null;
    if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
      token = authorizationHeader.substring(7);
    }
    return token;
  }

}

