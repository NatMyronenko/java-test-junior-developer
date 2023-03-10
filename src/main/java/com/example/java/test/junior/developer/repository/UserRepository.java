package com.example.java.test.junior.developer.repository;

import com.example.java.test.junior.developer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
