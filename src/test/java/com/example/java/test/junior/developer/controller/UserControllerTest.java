package com.example.java.test.junior.developer.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.dto.UserRequestDto;
import com.example.java.test.junior.developer.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class)
@WithMockUser
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  void testCreateUser() throws Exception {
    final var userRequest = new UserRequestDto("John", "Doe", "johndoe@example.com", "Pyps1111!");
    final var userDto = new UserDto(1L, "John", "Doe", "johndoe@example.com");
    when(userService.createUser(userRequest)).thenReturn(userDto);
    mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userRequest))
            .with(csrf())
            .with(user(userRequest.getEmail()).password(userRequest.getPassword()).roles("USER")))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.firstName", equalTo("John")))
        .andExpect(jsonPath("$.lastName", equalTo("Doe")))
        .andExpect(jsonPath("$.email", equalTo("johndoe@example.com")));
  }

  @Test
  void testGetUser() throws Exception {
    final var userDto = new UserDto(1L, "John", "Doe", "johndoe@example.com");
    when(userService.getUser(1L)).thenReturn(userDto);
    mockMvc.perform(get("/api/v1/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.firstName", equalTo("John")))
        .andExpect(jsonPath("$.lastName", equalTo("Doe")))
        .andExpect(jsonPath("$.email", equalTo("johndoe@example.com")));
  }

  @Test
  void testUpdateUser() throws Exception {
    final var userDto = new UserDto(1L, "John", "Doe", "johndoe@example.com");
    when(userService.updateUser(1L, userDto)).thenReturn(userDto);

    mockMvc.perform(put("/api/v1/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userDto))
            .with(csrf())
            .with(user(userDto.getEmail()).password("test-pass").roles("USER")))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.firstName", equalTo("John")))
        .andExpect(jsonPath("$.lastName", equalTo("Doe")))
        .andExpect(jsonPath("$.email", equalTo("johndoe@example.com")));
  }

  @Test
  void testDeleteUser() throws Exception {
    mockMvc.perform(delete("/api/v1/users/1")
            .with(csrf())
            .with(user("test-user").password("test-pass").roles("USER")))
        .andExpect(status().isOk());
    verify(userService).deleteUser(1L);
  }

  @Test
  void testGetUsers() throws Exception {
    final var userDto = new UserDto(1L, "John", "Doe", "johndoe@example.com");
    final var userList = List.of(userDto);
    when(userService.getAllUsers()).thenReturn(userList);
    mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$[0].id", equalTo(1)))
        .andExpect(jsonPath("$[0].firstName", equalTo("John")))
        .andExpect(jsonPath("$[0].lastName", equalTo("Doe")))
        .andExpect(jsonPath("$[0].email", equalTo("johndoe@example.com")));
  }
}
