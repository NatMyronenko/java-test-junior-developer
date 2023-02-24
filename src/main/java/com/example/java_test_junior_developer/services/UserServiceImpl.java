package com.example.java_test_junior_developer.services;

import com.example.java_test_junior_developer.model.User;
import com.example.java_test_junior_developer.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public void saveUser(User user) {
        userRepository.save(user);

    }

    @Override
    public User getUser(int id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
        //TODO  to create an exception
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);

    }
}
