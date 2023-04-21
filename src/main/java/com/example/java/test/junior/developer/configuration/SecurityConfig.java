package com.example.java.test.junior.developer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final RestTemplate restTemplate;
  @Value("${external.api.url}")
  private String externalApiUrl;

  public SecurityConfig(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/**").authenticated()
        .and()
        .addFilterBefore(new JwtAuthorizationFilter(restTemplate, externalApiUrl),
            UsernamePasswordAuthenticationFilter.class);
  }
}
