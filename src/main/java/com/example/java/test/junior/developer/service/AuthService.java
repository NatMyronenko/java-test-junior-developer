package com.example.java.test.junior.developer.service;


import com.example.java.test.junior.developer.dto.LogOutRequestDto;
import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.keycloak.KeycloakAuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final KeycloakAuthClient keycloakAuthClient;

  public void logout(LogOutRequestDto requestDto) {
    try {
      keycloakAuthClient.logout(requestDto.getAccessToken(), requestDto);
      log.info("Successfully logged out user with refresh token: {}", requestDto.getRefreshToken());
    } catch (Exception ex) {
      log.error("Error occurred while logging out user with refresh token: {}",
          requestDto.getRefreshToken(), ex);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to log out user",
          ex);
    }
  }

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

}
