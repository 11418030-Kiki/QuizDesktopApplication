package com.bananpiren.quiz.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by per on 2017-05-04.
 */

@Entity
public class QuizAnswers {

    @Id
    private int taId;
    private int taQuestionId;
    private String answer;
    private String correctAnswer;


    public QuizAnswers() {

    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getTaId() {
        return taId;
    }

    public void setTaId(int taId) {
        this.taId = taId;
    }

    public int getTaQuestionId() {
        return taQuestionId;
    }

    public void setTaQuestionId(int taQuestionId) {
        this.taQuestionId = taQuestionId;
    }




}
