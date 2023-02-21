package com.example.java_test_junior_developer.service;

import com.example.java_test_junior_developer.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);
}
