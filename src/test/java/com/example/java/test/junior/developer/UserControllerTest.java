package com.example.java.test.junior.developer;

import com.example.java.test.junior.developer.controller.UserController;
import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @SneakyThrows
    @Test
    void testCreateUser() {
        final var inputStream =
                new ClassPathResource("controller/user-request.json").getInputStream();
        final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        final var dto = UserDto.builder()
                .name("Tom")
                .surname("Disney")
                .email("tom@email")
                .build();
        final var response = UserDto.builder()
                .id(1)
                .name("Tom")
                .surname("Disney")
                .email("tom@email")
                .build();

        when(userService.createUser(dto)).thenReturn(response);

        mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Tom")))
                .andExpect(jsonPath("$.surname", equalTo("Disney")))
                .andExpect(jsonPath("$.email", equalTo("tom@email")));
    }


    @SneakyThrows
    @Test
    void testGetAllUsers() {
        final var response = UserDto.builder()
                .id(1)
                .name("Tom")
                .surname("Disney")
                .email("tom@email")
                .build();
        when(userService.getAllUsers()).thenReturn(List.of(response));

        mockMvc.perform(get("/user/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(1)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Tom")))
                .andExpect(jsonPath("$[0].surname", equalTo("Disney")))
                .andExpect(jsonPath("$[0].email", equalTo("tom@email")));
    }

    @SneakyThrows
    @Test
    void testUpdateUser() {
        final var inputStream =
                new ClassPathResource("controller/user-request.json").getInputStream();
        final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        final var dto = UserDto.builder()
                .name("Tom")
                .surname("Disney")
                .email("tom@email")
                .build();
        final var response = UserDto.builder()
                .id(1)
                .name("Tom")
                .surname("Disney")
                .email("tom@email")
                .build();

        when(userService.updateUser(1, dto)).thenReturn(response);

        mockMvc.perform(put("/user/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Tom")))
                .andExpect(jsonPath("$.surname", equalTo("Disney")))
                .andExpect(jsonPath("$.email", equalTo("tom@email")));

    }

    @SneakyThrows
    @Test
    void testDeleteUser() {
        mockMvc.perform(delete("/user/delete/1"))
                .andExpect(status().isOk());

        verify(userService).deleteUser(1);
    }

}
