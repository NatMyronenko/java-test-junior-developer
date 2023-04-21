package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.dto.UserRequestDto;
import com.example.java.test.junior.developer.mapper.UserMapper;
import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.repository.UserRepository;
import com.example.java.test.junior.developer.security.KeycloakAdminClient;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final KeycloakAdminClient keycloakAdminClient;


  @Transactional
  public UserDto createUser(UserRequestDto userRequestDto) {
    var userKeycloak = userMapper.toUserRepresentation(userRequestDto);
    keycloakAdminClient.createUser(userKeycloak);
    final User user = userMapper.toUser(userRequestDto);
    User savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }

  @Transactional
  public UserDto updateUser(Long id, UserDto userDto) {
    final User user = userMapper.toModel(userDto);
    user.setId(id);
    final User savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }

  @Transactional
  public UserDto getUser(Long id) {
    User user = userRepository.findById(id).orElse(null);
    return user != null ? userMapper.toDto(user) : null;
  }

  @Transactional
  public List<UserDto> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(userMapper::toDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}