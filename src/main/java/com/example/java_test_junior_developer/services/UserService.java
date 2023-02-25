package com.example.java_test_junior_developer.services;

import com.example.java_test_junior_developer.models.User;

import java.util.List;

public interface UserService {

    /**
     * This function returns a list of all users.
     *
     * @return A list of all users in the database.
     */
    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);


}
