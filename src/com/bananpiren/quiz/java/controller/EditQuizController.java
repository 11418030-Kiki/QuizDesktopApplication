package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.java.view.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditQuizController {

    @FXML
    private Label numberOfQuestionsLabel;

    @FXML
    private Label timeLimitLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Label numberOfQuizTakenLabel;

    @FXML
    private Button editButton;

    @FXML
    private Label quizNameLabel;

    @FXML
    private Label startDateLimitLabel;

    @FXML
    private Label endDateLimitLabel;

    @FXML
    private void initialize() {
        editButton.setOnAction(e -> {
            showEditQuizDialog();
        });

        // Delete quiz button
        deleteButton.setOnAction(e -> {
            System.out.println("delete quiz");
        });
    }

    private void showEditQuizDialog() {
        try {
            // Load FXML file to dialog stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("EditQuizDialog.fxml"));
            BorderPane page = loader.load();

            // Create the dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Redigera Quiz");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
