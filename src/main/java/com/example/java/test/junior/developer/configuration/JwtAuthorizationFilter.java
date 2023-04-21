package com.example.java.test.junior.developer.configuration;

import com.example.java.test.junior.developer.dto.UserDto;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private RestTemplate restTemplate;
  private String apiUrl;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = request.getHeader("Authorization");
    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
      try {
        // Send token to external API to get user details
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UserDto user = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, UserDto.class).getBody();

        // Set fetched user details in Security Context
        if (user != null) {
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
              user.getEmail(), null,
              Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      } catch (Exception e) {
        // Token is not valid
        SecurityContextHolder.clearContext();
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT token");
        return;
      }
    }

    filterChain.doFilter(request, response);
  }
}

