package com.example.java.test.junior.developer.model;

import java.util.Objects;
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
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "categories")
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id", nullable = false)
  private int id;

  @Column(name = "category", nullable = false)
  public String name;

  @ManyToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "language_id")
  private Language language;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return id == category.id && Objects.equals(name, category.name)
        && Objects.equals(language, category.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, language);
  }
}