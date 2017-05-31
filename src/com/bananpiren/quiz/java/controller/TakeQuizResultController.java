package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.TakeQuiz;
import com.bananpiren.quiz.Services.QuizService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anton on 2017-05-31.
 */
public class TakeQuizResultController {
    @FXML
    private Label quizNameLabel;

    @FXML
    private Text correctAnswersLabel;

    QuizService quizService = new QuizService();
    List<TakeQuiz> takeQuizList = new ArrayList<>();
    int questionCount;
    int points;
    String quizName;


    private void initialize(){
        int quizId = StartController.currentQuizId;
        takeQuizList = quizService.currentQuiz(quizId);

        points = Integer.parseInt(takeQuizList.get(0).getPoints());
        quizName = takeQuizList.get(0).getQuizName();
        questionCount = takeQuizList.size()/4;

        quizNameLabel.setText(quizName);
        correctAnswersLabel.setText("Du fick: "+points+" av "+ questionCount+" poäng möjliga");
    }
}
