package com.bananpiren.quiz.java.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javax.swing.event.ChangeListener;

public class CreateQuizController {

    private int questionNumber = 1;
    private int answerNumber = 1;

    @FXML
    private Slider sliderTime;

    @FXML
    private Button buttonCreateQuiz;

    @FXML
    private VBox vboxAddQuestions;

    @FXML
    private Label labelMinutes;

    @FXML
    private Button buttonAddQuestion;

    @FXML
    private void initialize() {

        // Button add Question
        buttonAddQuestion.setOnAction(e-> {
            addQuestion();
        });

        // Button Create Quiz
        buttonCreateQuiz.setOnAction(e -> {
            createQuit();
        });

        // Show slidervalue at label
        sliderTime.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelMinutes.setText(newValue.intValue() + " minuter");
        });

    }

    private void addQuestion() {

        TextField newQuestion = new TextField();
        newQuestion.setPromptText("Fråga " + questionNumber++);
        TextField newAnswer = new TextField();
        newAnswer.setPromptText("Svar " + answerNumber++);
        TextField newAnswer2 = new TextField();
        newAnswer2.setPromptText("Svar " + answerNumber++);
        TextField newAnswer3 = new TextField();
        newAnswer3.setPromptText("Svar " + answerNumber++);
        TextField newAnswer4 = new TextField();
        newAnswer4.setPromptText("Svar " + answerNumber++);
        vboxAddQuestions.getChildren().addAll(newQuestion, newAnswer, newAnswer2, newAnswer3, newAnswer4);
    }

    private void createQuit() {
        // todo add logic for taking in information and adding quiz to database
    }
}
