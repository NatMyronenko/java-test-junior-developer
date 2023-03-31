package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.LoginRequestDto;
import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<LoginResponseDto> generateToken(
      @Valid @RequestBody LoginRequestDto loginRequest, BindingResult result) {
    if (result.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
    }
    LoginResponseDto response = authService.generateLoginResponse(loginRequest.getEmail(),
        loginRequest.getPassword());
    return ResponseEntity.ok(response);
  }
}





