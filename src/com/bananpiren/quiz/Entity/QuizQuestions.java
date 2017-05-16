package com.bananpiren.quiz.Entity;

import javax.persistence.*;

/**
 * Created by per on 2017-05-04.
 */

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



}
