package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.LoginRequestDto;
import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.security.KeycloakAuthClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

  private final KeycloakAuthClient keycloakAuthClient;

  public AuthController(KeycloakAuthClient keycloakAuthClient) {
    this.keycloakAuthClient = keycloakAuthClient;
  }

  @PostMapping("/login")
  public LoginResponseDto generateToken(@RequestBody LoginRequestDto loginRequest) {
    String accessToken = keycloakAuthClient.getAccessToken(loginRequest.getEmail(), loginRequest.getPassword()).block();
    if (accessToken == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }
    LoginResponseDto response = new LoginResponseDto();
    response.setAccessToken(accessToken);
    return response;
  }
}



