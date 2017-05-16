package com.bananpiren.quiz.Entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Created by per on 2017-05-04.
 */


@Entity
public class QuestionAnswers {

    // increase 1
    @TableGenerator(
            name = "autoGenerator",
            allocationSize = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "autoGenerator")
    private int answerId;
    @NotNull
    private String answer;
    @NotNull
    private boolean correctAnswer;


    public QuestionAnswers() {

    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int taId) {
        this.answerId = taId;
    }




}
