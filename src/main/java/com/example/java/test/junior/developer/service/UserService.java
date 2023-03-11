package com.example.java.test.junior.developer.service;
import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.mapper.UserMapper;
import com.example.java.test.junior.developer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    public UserDto createUser(UserDto dto) {
        final var user = userMapper.toEntity(dto);
        final var saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        final var users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto updateUser(int id, UserDto dto) {
        final var user = userMapper.toEntity(dto);
        user.setId(id);
        final var saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}

