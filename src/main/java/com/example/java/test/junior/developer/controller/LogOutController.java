package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.LogOutRequestDto;
import com.example.java.test.junior.developer.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/logout")
@Slf4j
@RequiredArgsConstructor
public class LogOutController {

  private final AuthService authService;

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void logout(@RequestBody @Valid LogOutRequestDto requestDto) {
    authService.logout(requestDto);
    log.info("User logged out successfully");
  }
}



