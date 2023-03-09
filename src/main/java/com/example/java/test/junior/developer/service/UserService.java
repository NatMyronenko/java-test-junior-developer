package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    public boolean createUser(User user) {
//        String email = user.getEmail();
//        if (userRepository.findByEmail(email) != null) return false;
//        user.setActive(true);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.getRoles().add(Role.ROLE_USER);
//        // user.getRoles().add(Role.ROLE_ADMIN);
//        log.info("Saving new User with email: {}", email);
//        userRepository.save(user);
//        return true;
    }

