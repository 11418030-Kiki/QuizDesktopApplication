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
    private DatePicker datePickerEndDate;

    @FXML
    private Button buttonCreateQuiz;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private VBox vboxAddQuestions;

    @FXML
    private TextField textFieldQuizName;

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
            createQuiz();
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

    private void createQuiz() {
        // todo add logic for taking in information and adding quiz to database
        StringBuilder warnings = new StringBuilder();

        String quizName = textFieldQuizName.getText();

        // Check if Quiz name is entered
        if(quizName.isEmpty() ) {
            warnings.append("Quiznamn saknas!\n");
        } else {
            quizName = textFieldQuizName.getText();
        }

        // Check if Starting date is entered
        if(datePickerStartDate != null ) {
            warnings.append("Startdatum saknas!\n");
        } else {
            // create startDate for jpa
        }

        // Check if Ending date is entered
        if(datePickerStartDate != null ) {
            warnings.append("Slutdatum saknas!\n");
        } else {
            // create endDate for jpa
        }

        if(warnings.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Alla fält är inte ifyllda!\n" + warnings.toString());
            alert.showAndWait();
            return;
        } else {
            // Create quiz "JPA"
        }

    }
}
