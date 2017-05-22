//TODO: Add class info

package com.bananpiren.quiz.Entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "autoGenerator")
    private int quizId;

    @NotNull
    private String quizName;
    @NotNull
    private int timeLimit;
    @NotNull
    private String quizStartDate;
    @NotNull
    private String quizEndDate;

    public Quiz(String quizName, int timeLimit, String quizStartDate, String quizEndDate) {
        this.quizName = quizName;
        this.timeLimit = timeLimit;
        this.quizStartDate = quizStartDate;
        this.quizEndDate = quizEndDate;
    }

    public Quiz() {}

    // Getters and setters
    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int testId) {
        this.quizId = testId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String testName) {
        this.quizName = testName;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getQuizStartDate() {
        return quizStartDate;
    }

    public void setQuizStartDate(String testStartDate) {
        this.quizStartDate = testStartDate;
    }

    public String getQuizEndDate() {
        return quizEndDate;
    }

    public void setQuizEndDate(String testEndDate) {
        this.quizEndDate = testEndDate;
    }
}