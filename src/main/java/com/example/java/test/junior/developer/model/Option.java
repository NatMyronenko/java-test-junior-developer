package com.example.java.test.junior.developer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "options")
public class Option {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "option_id", unique = true, nullable = false)
  private Long id;

  @Column(name = "answer", nullable = false)
  private String answer;

  @Column(name = "question_id", nullable = false)
  private String questionId;

  @Column(name = "correct", unique = true, nullable = false)
  private Boolean isCorrect;
}
