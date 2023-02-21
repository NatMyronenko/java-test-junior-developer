package com.example.java_test_junior_developer.services;

import com.example.java_test_junior_developer.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);
}
