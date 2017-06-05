package com.bananpiren.quiz.Entity;

import com.sun.istack.internal.NotNull;
import javafx.scene.control.CheckBox;

import javax.persistence.*;

@Entity
public class CorrectQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int correctId;
    @NotNull
    private int answerId;
    @NotNull
    private int userId;
    @NotNull
    private String correctAnswer;
    @NotNull
    private boolean userAnswer;


    public int getCorrectId() {
        return correctId;
    }

    public void setCorrectId(int correctId) {
        this.correctId = correctId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Boolean userAnswer) {
        this.userAnswer = userAnswer;
    }
}
