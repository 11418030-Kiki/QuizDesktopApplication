package com.bananpiren.quiz.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by per on 2017-05-04.
 */

@Entity
public class Questions {

    @Id
    private int questionTestId;
    private int questionId;
    private String question;


    public Questions() {

    }

    public int getQuestionTestId() {
        return questionTestId;
    }

    public void setQuestionTestId(int questionTestId) {
        this.questionTestId = questionTestId;
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
