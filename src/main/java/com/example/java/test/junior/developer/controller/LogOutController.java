package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.security.KeycloakRestClient;
import com.example.java.test.junior.developer.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/logout")
@Slf4j
@RequiredArgsConstructor
public class LogOutController {

  private final AuthService authService;
  private final KeycloakRestClient keycloakClient;


  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void logout(@RequestHeader("Authorization") String authorizationHeader,
      @RequestBody String refreshToken) {
    log.info("Received logout request with authorization header: {}", authorizationHeader);
    log.info("Received logout request with refresh token: {}", refreshToken);
    String accessToken = authService.getAccessTokenFromHeader(authorizationHeader);
    log.info("Access token extracted from authorization header: {}", accessToken);

    keycloakClient.logout(refreshToken, accessToken);
    log.info("Logout request sent to Keycloak with refresh token: {}", refreshToken);

  }
}
