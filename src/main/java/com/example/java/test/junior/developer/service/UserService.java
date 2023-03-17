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

//TODO: Перепишу нормально під мапер коли посплю )
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    @Transactional
    public UserDto createUser(UserRequestDto userRequestDto){
        userMapper.toUserRepresentation(userRequestDto);
        final UserDto userDto = userMapper.toDto(userRequestDto);
        userRepository.save(userMapper.toModel(userDto));
        return userDto;
    }
    @Transactional
    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }
    @Transactional
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
