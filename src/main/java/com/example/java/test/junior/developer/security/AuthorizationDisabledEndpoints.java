package com.example.java.test.junior.developer.security;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "security.endpoints")
public class AuthorizationDisabledEndpoints {

  private List<String> get = new ArrayList<>();
  private List<String> post = new ArrayList<>();
  private List<String> put = new ArrayList<>();
  private List<String> delete = new ArrayList<>();

}