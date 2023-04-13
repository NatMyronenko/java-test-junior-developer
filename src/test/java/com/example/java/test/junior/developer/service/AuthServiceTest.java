//package com.example.java.test.junior.developer.service;
//
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import com.example.java.test.junior.developer.dto.LoginResponseDto;
//import com.example.java.test.junior.developer.security.KeycloakAuthClient;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.web.server.ResponseStatusException;
//
//public class AuthServiceTest {
//
//  private AuthService authService;
//  private KeycloakAuthClient keycloakAuthClient;
//
//  @BeforeEach
//  public void setUp() {
//    keycloakAuthClient = mock(KeycloakAuthClient.class);
//    authService = new AuthService(keycloakAuthClient);
//  }
//
//  @Test
//  public void testGenerateLoginResponse_Successful() {
//    // Arrange
//    String email = "test@test.com";
//    String password = "Test123!";
//    LoginResponseDto responseDto = new LoginResponseDto();
//    when(keycloakAuthClient.getAccessToken(email, password)).thenReturn(responseDto);
//
//    // Act
//    LoginResponseDto result = authService.generateLoginResponse(email, password);
//
//    // Assert
//    assertSame(responseDto, result);
//  }
//
//  @Test
//  public void testGenerateLoginResponse_InvalidCredentials() {
//    // Arrange
//    String email = "test@test.com";
//    String password = "Test123!";
//    when(keycloakAuthClient.getAccessToken(email, password))
//        .thenThrow(new RuntimeException());
//
//    // Act & Assert
//    assertThrows(ResponseStatusException.class, () -> {
//      authService.generateLoginResponse(email, password);
//    });
//  }
//}
