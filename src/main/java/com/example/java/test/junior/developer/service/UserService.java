package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.exception.UserNotFoundException;
import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(User user) {
        log.info("Saving new {}", user);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(int id, User user) {
        Optional<User> existUser = userRepository.findById(id);
        if (existUser.isPresent()) {
            User updatedUser = existUser.get();
            updatedUser.setName(user.getName());
            return userRepository.save(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with id " + id);
        }
    }

    public void deleteUser(int id) {
        Optional<User> existUser = userRepository.findById(id);
        if (existUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with id " + id);
        }
    }
}


