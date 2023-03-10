package com.example.java.test.junior.developer.repository;

import com.example.java.test.junior.developer.model.Language;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {


}
