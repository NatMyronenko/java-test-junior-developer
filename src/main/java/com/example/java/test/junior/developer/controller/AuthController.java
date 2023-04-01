package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.LoginRequestDto;
import com.example.java.test.junior.developer.dto.LoginResponseDto;
import com.example.java.test.junior.developer.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public LoginResponseDto generateToken(@Valid @RequestBody LoginRequestDto loginRequest) {
    return authService.generateLoginResponse(loginRequest.getEmail(), loginRequest.getPassword());
  }
}




