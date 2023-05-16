package com.example.java.test.junior.developer.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.keycloak.KeycloakAuthClient;
import com.example.java.test.junior.developer.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Import(SecurityConfig.class)
@WithMockUser
public class AuthServiceTest {

  private AuthService authService;
  private KeycloakAuthClient keycloakAuthClient;

  @BeforeEach
  public void setUp() {
    keycloakAuthClient = mock(KeycloakAuthClient.class);
    authService = new AuthService(keycloakAuthClient);
  }

  @Test
  public void testGenerateLoginResponse_Successful() {
    // Arrange
    String email = "test@test.com";
    String password = "Test123!";
    LoginResponseDto responseDto = new LoginResponseDto();
    when(keycloakAuthClient.getAccessToken(email, password)).thenReturn(responseDto);

    // Act
    LoginResponseDto result = authService.generateLoginResponse(email, password);

    // Assert
    assertSame(responseDto, result);
  }

}
