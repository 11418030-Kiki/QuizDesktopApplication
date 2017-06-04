package com.bananpiren.quiz.Entity;

/**
 * Created by Anton on 2017-06-01.
 */
public class StatisticsUser {
    private String firstName;
    private String lastName;
    private String correctPercentage;
    private int userId;

    public StatisticsUser(String firstName, String lastName, String correctPercentage, int userId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.correctPercentage = correctPercentage;
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCorrectPercentage() {
        return correctPercentage;
    }

    public void setCorrectPercentage(String correctPercentage) {
        this.correctPercentage = correctPercentage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }
}
