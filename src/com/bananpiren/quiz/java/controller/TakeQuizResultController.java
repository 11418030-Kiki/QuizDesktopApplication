package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.TakeQuiz;
import com.bananpiren.quiz.Services.QuizService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anton on 2017-05-31.
 */
public class TakeQuizResultController {
    @FXML
    private Label quizNameLabel;

    @FXML
    private Text correctQuizAnswerText;

    private QuizService quizService = new QuizService();
    private List<TakeQuiz> takeQuizList = new ArrayList<>();
    private int questionCount;
    private int points;
    private String quizName;

    @FXML
    private void initialize(){
        int quizId = StartController.currentQuizId;
        correctQuizAnswerText.setText("testar bara");
        correctQuizAnswerText.setTextAlignment(TextAlignment.RIGHT);
    }
}
