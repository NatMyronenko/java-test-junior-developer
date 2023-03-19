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
  private int id;

  @Column(name = "category", nullable = false)
  public String name;

  @ManyToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "language_id")
  private Language language;

}