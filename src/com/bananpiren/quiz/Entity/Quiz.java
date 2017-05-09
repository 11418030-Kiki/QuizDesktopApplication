package com.bananpiren.quiz.Entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by per on 2017-05-04.
 */

@Entity
@Table
public class Quiz {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int quizId;

    private String quizName;
    private int timeLimit;
    private String quizStartDate;
    private String quizEndDate;

    public Quiz() {

    }

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
