package com.example.java.test.junior.developer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class LoginResponseDto {

  @JsonProperty("session_state")
  private String sessionState;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_in")
  private Long expiresIn;

  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("refresh_expires_in")
  private Long refreshExpiresIn;

  @PostConstruct
  public void init() {
    log.info("Created LoginResponseDto with accessToken={}", accessToken);
  }

}
