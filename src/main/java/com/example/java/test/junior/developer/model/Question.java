package com.example.java.test.junior.developer.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "questions")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_id", nullable = false)
  private Long id;

  @Column(name = "question", nullable = false)
  public String name;

  @ManyToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(cascade = CascadeType.DETACH,
      mappedBy = "question",
      fetch = FetchType.LAZY)
  private List<Option> listOptions;

}
