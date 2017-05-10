package com.bananpiren.quiz.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditQuizDialogController {

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
    private void initialize() {

        // Save button
        saveButton.setOnAction(e -> {
            System.out.println("Spara quiz ändringar");
        });

        // Cancel button
        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}
