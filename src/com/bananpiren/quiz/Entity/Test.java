package com.bananpiren.quiz.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by per on 2017-05-04.
 */

@Entity
public class Test {

    @Id
    private int testId;
    private int timeLimit;
    private int testStartDate;
    private int testEndDate;
    private String testName;

    public Test() {

    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getTestStartDate() {
        return testStartDate;
    }

    public void setTestStartDate(int testStartDate) {
        this.testStartDate = testStartDate;
    }

    public int getTestEndDate() {
        return testEndDate;
    }

    public void setTestEndDate(int testEndDate) {
        this.testEndDate = testEndDate;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
