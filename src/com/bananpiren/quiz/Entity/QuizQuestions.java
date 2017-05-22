//TODO: Add class info

package com.bananpiren.quiz.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class QuizQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int questionId;

    private String question;
    private String questionType;

    @ManyToOne()
    private Quiz quiz;

    public QuizQuestions(String question, Quiz quiz) {
        this.question = question;
        this.quiz = quiz;
    }

    //TODO: Add question type to create quiz
    public QuizQuestions(String question, String questionType, Quiz quiz) {
        this.question = question;
        this.questionType = questionType;
        this.quiz = quiz;
    }

    public QuizQuestions() {
        super();
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }



}
