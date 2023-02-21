package com.example.java_test_junior_developer.repository;

import com.example.java_test_junior_developer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
