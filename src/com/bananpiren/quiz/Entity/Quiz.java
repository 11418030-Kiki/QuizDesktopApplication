package com.bananpiren.quiz.Entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Quiz {

    // increase 1
    @TableGenerator(
        name = "autoGenerator",
        allocationSize = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "autoGenerator")
    private int quizId;
    @NotNull
    private String quizName;
    @NotNull
    private int timeLimit;
    @NotNull
    private String quizStartDate;
    @NotNull
    private String quizEndDate;


    @OneToMany( targetEntity=QuizQuestions.class )
    private List questionsList;


    public Quiz(int quizId, String quizName, int timeLimit, String quizStartDate, String quizEndDate) {
        super();
        this.quizId = quizId;
        this.quizName = quizName;
        this.timeLimit = timeLimit;
        this.quizStartDate = quizStartDate;
        this.quizEndDate = quizEndDate;
    }

    public Quiz() {super();}

    // Getters and setters
    public List getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List questionsList) {
        this.questionsList = questionsList;
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