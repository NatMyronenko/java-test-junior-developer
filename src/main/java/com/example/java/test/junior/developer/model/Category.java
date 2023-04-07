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

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "categories")
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id", nullable = false)
  private Long id;

  @Column(name = "category", nullable = false)
  public String name;

  @ManyToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "language_id", referencedColumnName = "language_id", nullable = false)
  private Language language;

  @OneToMany(cascade = CascadeType.DETACH,
      mappedBy = "category",
      fetch = FetchType.LAZY)
  private List<Question> listQuestions;

}

