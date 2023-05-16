package com.example.java.test.junior.developer.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@EnableConfigurationProperties(AuthorizationDisabledEndpoints.class)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
    http.oauth2ResourceServer().jwt();
    return http.csrf().disable().build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer(
      AuthorizationDisabledEndpoints authorizationDisabledEndpoints
  ) {
    return (web) -> web.ignoring()
        .requestMatchers(authorizationDisabledEndpoints.getGet().stream()
            .map(AntPathRequestMatcher::new).toArray(RequestMatcher[]::new))
        .requestMatchers(authorizationDisabledEndpoints.getPost().stream()
            .map(AntPathRequestMatcher::new).toArray(RequestMatcher[]::new))
        .requestMatchers(authorizationDisabledEndpoints.getPut().stream()
            .map(AntPathRequestMatcher::new).toArray(RequestMatcher[]::new))
        .requestMatchers(authorizationDisabledEndpoints.getDelete().stream()
            .map(AntPathRequestMatcher::new).toArray(RequestMatcher[]::new));
  }
}
