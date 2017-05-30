package com.bananpiren.quiz.Entity;


import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userQuizId; // hämta id
    @NotNull
    private int userId; // hämta användare KLAR
    @NotNull
    private String QuizName; // hämta quizname KLAR
    @NotNull
    private int points; // räkna ihop poäng


    public int getUserQuizId() {
        return userQuizId;
    }

    public void setUserQuizId(int userQuizId) {
        this.userQuizId = userQuizId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String quizName) {
        QuizName = quizName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
