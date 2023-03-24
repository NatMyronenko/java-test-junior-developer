package com.example.java.test.junior.developer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Builder
@Value
@RequiredArgsConstructor
public class UserRequestDto {

  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z][-a-zA-Z\\s]{0,22}[a-zA-Z]$")
  @NonNull String firstName;

  @Pattern(regexp = "^[a-zA-Z][-a-zA-Z\\s]{0,22}[a-zA-Z]$")
  @NonNull String lastName;

  @Email(regexp = "^(?=.{10,63}$)(?!.*\\s)"
      + "[a-zA-Z0-9!#$%&'*+\\-=?^_`{|}~]+"
      + "(?:[.\\-][a-zA-Z0-9!#$%&'*+\\-=?^_`{|}~]+)*"
      + "@[a-z0-9](?:[a-z0-9\\-]*[a-z0-9])*"
      + "(?:\\.[a-z0-9](?:[a-z0-9\\-]*[a-z0-9])*)+",
      message = "Email should be valid")
  @NonNull String email;

  @NotEmpty
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)"
      + "(?=.*[!\"#$%&'()*+,\\-./:;<=>?@[\\\\]^_{|}~])"
      + "[A-Za-z\\d!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~]{8,32}$" )
  @NonNull String password;
}