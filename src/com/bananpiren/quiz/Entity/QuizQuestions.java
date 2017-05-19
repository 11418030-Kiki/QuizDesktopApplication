package com.bananpiren.quiz.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class QuizQuestions {

    // increase 1
    @TableGenerator(
            name = "autoGenerator",
            allocationSize = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "autoGenerator")
    private int questionId;
    private String question;

    @OneToMany( targetEntity = QuestionAnswers.class, orphanRemoval = true)
    private List answerList;


    public QuizQuestions() {
        super();
    }

    public List getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List answerList) {
        this.answerList = answerList;
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



}
