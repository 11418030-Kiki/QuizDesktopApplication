package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.QuizQuestions;
import com.bananpiren.quiz.Services.QuestionService;
import com.bananpiren.quiz.Services.QuizService;
import com.bananpiren.quiz.java.model.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class EditQuizDialogController {

    private final ObservableList<QuizQuestions> quiz = FXCollections.observableArrayList();
    private final ObservableList<QuizQuestions> questions = FXCollections.observableArrayList();

    private static int currentQuiz;

    private String quizName;
    private int timeLimit;
    private String quizStartDate;
    private String quizEndDate;
    private String selfcorrecting;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker endDateDatePicker;

    @FXML
    private TextField timeLimitTextField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField quizNameTextField;

    @FXML
    private DatePicker startDateDatePicker;

    @FXML
    private CheckBox selfCorrectingCheckBox;


    @FXML
    private ListView<QuizQuestions> questionsList;

    public EditQuizDialogController() {

    }

    @FXML
    private void initialize() {
        // Get selected quizId
        currentQuiz = EditQuizController.getStoredQuizId();

        // Load current quiz from database
        loadQuiz();

        // Set quiz properties
        setQuizProperties();

        // Load selected quiz questions and set table
        loadTableData();

        // Save button
        saveButton.setOnAction(e -> {
            String selfCorrectingCheckBoxValue;
            if (selfCorrectingCheckBox.isSelected()) {
                selfCorrectingCheckBoxValue = "yes";
            } else {
                selfCorrectingCheckBoxValue = "no";
            }

            int timeLimit = Integer.parseInt(timeLimitTextField.getText());

            try {
                QuizService.updateQuiz(currentQuiz, quizNameTextField.getText(), timeLimit, startDateDatePicker.getValue().toString(), endDateDatePicker.getValue().toString(), selfCorrectingCheckBoxValue);
                Alerts.informationAlert("Succe!", "Quizet uppdaterades", "");

                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            } catch (Exception ex) {
                Alerts.errorAlert("Fel", "Det gick inte att spara uppdateringar", "Testa igen");
            }
        });

        // Cancel button
        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

    private void setQuizProperties() {
        quizNameTextField.setText(quizName);

        startDateDatePicker.setValue(LocalDate.parse(quizStartDate));
        endDateDatePicker.setValue(LocalDate.parse(quizEndDate));

        timeLimitTextField.setText(String.valueOf(timeLimit));

        switch(selfcorrecting) {
            case "yes" : selfCorrectingCheckBox.setSelected(true);
                break;
            case "no" : selfCorrectingCheckBox.setSelected(false);
                break;
            default: selfCorrectingCheckBox.setSelected(false);
        }
    }

    // Load selected quiz and set variables
    private void loadQuiz() {
        Quiz quiz = QuizService.findQuiz(currentQuiz);

        quizName = quiz.getQuizName();
        timeLimit = quiz.getTimeLimit();
        quizStartDate = quiz.getQuizStartDate();
        quizEndDate = quiz.getQuizEndDate();
        selfcorrecting = quiz.getSelfcorrecting();
        System.out.println(selfcorrecting);
    }

    private void loadTableData() {
        // Get questions from selected quiz
        List<QuizQuestions> tempQuestions = QuestionService.read(currentQuiz);

        // Add to observablelist
        questions.addAll(tempQuestions);

        // Show in listview
        questionsList.setItems(questions);
    }
}
