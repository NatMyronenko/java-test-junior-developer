package com.example.java.test.junior.developer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "options")
@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", nullable = false)
    private Long id;

    @Column(name = "answer", nullable = false)
    public String answer;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "correct", nullable = false)
    public boolean correct;

}
