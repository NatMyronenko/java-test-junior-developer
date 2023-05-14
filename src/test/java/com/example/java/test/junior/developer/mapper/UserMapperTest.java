package com.example.java.test.junior.developer.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.dto.UserRequestDto;
import com.example.java.test.junior.developer.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class UserMapperTest {

  private UserMapper userMapper;

  @BeforeEach
  void setUp() {
    userMapper = new UserMapper();
  }

  @Test
  void toModel_ValidDto_ReturnsModel() {
    UserDto userDto = new UserDto(null, "John", "Doe", "johndoe@example.com");

    User result = userMapper.toModel(userDto);

    assertThat(result.getId()).isNull();
    assertThat(result.getFirstName()).isEqualTo(userDto.getFirstName());
    assertThat(result.getLastName()).isEqualTo(userDto.getLastName());
    assertThat(result.getEmail()).isEqualTo(userDto.getEmail());
  }

  @Test
  void toDto_ValidModel_ReturnsDto() {
    User user = User.builder()
        .id(1L)
        .firstName("John")
        .lastName("Doe")
        .email("johndoe@example.com")
        .build();

    UserDto result = userMapper.toDto(user);

    assertEquals(user.getId(), result.getId());
    assertEquals(user.getFirstName(), result.getFirstName());
    assertEquals(user.getLastName(), result.getLastName());
    assertEquals(user.getEmail(), result.getEmail());
  }

  @Test
  void toUser_ValidUserRequestDto_ReturnsUser() {
    UserRequestDto userRequestDto = new UserRequestDto("John", "Doe", "johndoe@example.com",
        "Pyps1111!");

    User result = userMapper.toUser(userRequestDto);

    assertThat(result.getId()).isNull();
    assertThat(result.getFirstName()).isEqualTo(userRequestDto.getFirstName());
    assertThat(result.getLastName()).isEqualTo(userRequestDto.getLastName());
    assertThat(result.getEmail()).isEqualTo(userRequestDto.getEmail());
  }

  @Test
  void toUserRepresentation_ValidUserRequestDto_ReturnsUserRepresentation() {
    UserRequestDto userRequestDto = new UserRequestDto("John", "Doe", "johndoe@example.com",
        "password");

    UserRepresentation result = userMapper.toUserRepresentation(userRequestDto);

    assertEquals(userRequestDto.getEmail(), result.getEmail());
    assertTrue(result.isEnabled());
    assertEquals(1, result.getCredentials().size());
    CredentialRepresentation credential = result.getCredentials().get(0);
    assertEquals(CredentialRepresentation.PASSWORD, credential.getType());
    assertEquals(userRequestDto.getPassword(), credential.getValue());
    assertFalse(credential.isTemporary());
  }
}

