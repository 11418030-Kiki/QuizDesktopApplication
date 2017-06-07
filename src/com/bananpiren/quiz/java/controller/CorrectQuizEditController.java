package com.bananpiren.quiz.java.controller;


import com.bananpiren.quiz.Services.CorrectQuizService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class CorrectQuizEditController {

    @FXML
    private Label quizNameLabel;

    @FXML
    private Button sendPoints;

    @FXML
    private TextField points;

    @FXML
    private void initialize() {

        quizNameLabel.setText("Rätta quiz");

        sendPoints.setOnAction(event -> {
            quizNameLabel.setText("Quizet är rättat");
            int id = CorrectQuizController.getQuizId();
            CorrectQuizService.updatePoints("" + id, Integer.parseInt(points.getText()));
            points.setDisable(true);
        });


    }
}
