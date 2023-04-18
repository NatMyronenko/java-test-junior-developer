package com.example.java.test.junior.developer.repository;

import com.example.java.test.junior.developer.model.UserPassedTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPassedTestRepository extends JpaRepository<UserPassedTest, Long> {
}
