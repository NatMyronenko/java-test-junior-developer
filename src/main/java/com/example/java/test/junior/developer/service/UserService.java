package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.dto.UserRequestDto;
import com.example.java.test.junior.developer.mapper.UserMapper;
import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public UserDto createUser(UserRequestDto userRequestDto) {
        userMapper.toUserRepresentation(userRequestDto);
        final UserDto userDto = userMapper.toDto(userRequestDto);
        User savedUser = userRepository.save(userMapper.toModel(userDto));
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        final User user = userMapper.toModel(userDto);
        user.setId(id);
        final User savedUser = userRepository.save(user);  // Не зовсім розумію логіку такого написання/ Для перевірки чи зберегли в репо?
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        return userMapper.toDto(user);
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
