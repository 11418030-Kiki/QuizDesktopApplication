package com.bananpiren.quiz.java.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CorrectQuizEditController {

    @FXML
    private Label quizNameLabel;

    @FXML
    private void initialize(){

        quizNameLabel.setText("Label");

    }
}
