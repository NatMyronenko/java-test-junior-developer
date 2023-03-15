package com.example.java.test.junior.developer.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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


