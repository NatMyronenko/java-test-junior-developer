package com.example.java.test.junior.developer.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "languages")
@Entity
public class Language {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "language_id", nullable = false)
  private Long id;

  @Column(name = "language", nullable = false)
  public String name;

  @OneToMany(cascade = CascadeType.ALL,
      mappedBy = "language",
      fetch = FetchType.EAGER)
  private List<Category> listCategories;
}