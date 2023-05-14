package com.example.java.test.junior.developer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.dto.UserRequestDto;
import com.example.java.test.junior.developer.keycloak.KeycloakAdminClient;
import com.example.java.test.junior.developer.mapper.UserMapper;
import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserMapper userMapper;

  @Mock
  private UserRepository userRepository;

  @Mock
  private KeycloakAdminClient keycloakAdminClient;

  @InjectMocks
  private UserService userService;

  @Test
  void createUserTest() {
    UserRequestDto userRequestDto = new UserRequestDto("Tom", "Tom", "tom@gmail.com", "Pyps1111!");
    UserRepresentation userRepresentation = new UserRepresentation();
    User user = new User();
    User savedUser = new User();
    UserDto expectedDto = new UserDto(1L, "Tom", "Tom", "tom@gmail.com");
    when(userMapper.toUserRepresentation(userRequestDto)).thenReturn(userRepresentation);
    doNothing().when(keycloakAdminClient).createUser(userRepresentation);
    when(userMapper.toUser(userRequestDto)).thenReturn(user);
    when(userRepository.save(user)).thenReturn(savedUser);
    when(userMapper.toDto(savedUser)).thenReturn(expectedDto);
    UserDto actualDto = userService.createUser(userRequestDto);
    assertEquals(expectedDto, actualDto);
  }


  @Test
  void updateUserTest() {
    Long id = 1L;
    UserDto userDto = new UserDto(1L, "Tom", "Tom", "tom@gmail.com");
    User user = new User();
    User savedUser = new User();

    final UserDto expectedDto = new UserDto(1L, "Tom", "Tom", "tom@gmail.com");
    when(userMapper.toModel(userDto)).thenReturn(user);
    user.setId(id);
    when(userRepository.save(user)).thenReturn(savedUser);
    when(userMapper.toDto(savedUser)).thenReturn(expectedDto);
    UserDto actualDto = userService.updateUser(id, userDto);
    assertEquals(expectedDto, actualDto);
  }


  @Test
  void getUserTest() {
    Long id = 1L;
    User user = new User();
    UserDto expectedDto = new UserDto(1L, "Tom", "Tom", "tom@gmail.com");
    when(userRepository.findById(id)).thenReturn(Optional.of(user));
    when(userMapper.toDto(user)).thenReturn(expectedDto);
    UserDto actualDto = userService.getUser(id);
    assertEquals(expectedDto, actualDto);
  }

  @Test
  void getAllUsersTest() {
    List<User> users = new ArrayList<>();
    User user = new User();
    users.add(user);
    List<UserDto> expectedDtos = new ArrayList<>();
    UserDto expectedDto = new UserDto(1L, "Tom", "Tom", "tom@gmail.com");
    expectedDtos.add(expectedDto);
    when(userRepository.findAll()).thenReturn(users);
    when(userMapper.toDto(user)).thenReturn(expectedDto);
    List<UserDto> actualDtos = userService.getAllUsers();
    assertEquals(expectedDtos, actualDtos);
  }

  @Test
  void deleteUserTest() {
    Long id = 1L;
    userService.deleteUser(id);
    verify(userRepository).deleteById(id);
  }
}

