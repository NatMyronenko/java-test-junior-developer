package com.example.java.test.junior.developer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "user_quiz_answers")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "option_id")
    private Option option;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
}
