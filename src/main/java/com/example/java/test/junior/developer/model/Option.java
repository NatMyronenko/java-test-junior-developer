package com.example.java.test.junior.developer.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
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

  @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
  @JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false)
  private Question question;

  @Column(name = "correct", nullable = false)
  private Boolean isCorrect;

}
