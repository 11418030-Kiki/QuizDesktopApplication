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
    private int userQuizId; // generate id
    @NotNull
    private int userId; // userId V
    @NotNull
    private String userName; // userId V
    @NotNull
    private String QuizName; // quizName V
    @NotNull
    private String quizId; // quizID V
    @NotNull
    private int noOfQuestions; //
    @NotNull
    private int maxPoints; //
    @NotNull
    private int points; // number of points


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
