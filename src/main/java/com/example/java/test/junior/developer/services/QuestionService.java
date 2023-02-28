package com.example.java.test.junior.developer.services;

import com.example.java.test.junior.developer.models.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private List<Question> questions = new  ArrayList();
    private long ID =0;
    {
        questions.add(new Question(++ID,"Tom","саме питання",true,false,true,2));
        questions.add(new Question(++ID,"Що таке перегрузка?","саме питання",true,false,false,4));
    }
 //получение списка всех вопросов
 public List<Question> listQuestions(){
        return questions;
 }
 public void saveQuestion(Question question){
        question.setId(++ID);
        questions.add(question);
 }
 public void deleteQuestion(Long id){
        questions.removeIf(question -> question.getId().equals(id));
 }

    public Question getQuestionById(Long id) {
        for (Question question : questions) {
            if (question.getId().equals(id)) return question;
        }
        return null;
    }
}
