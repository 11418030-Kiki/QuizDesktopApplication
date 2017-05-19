package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Services.QuizService;
import com.bananpiren.quiz.java.view.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditQuizController {

    private QuizService quizService = new QuizService();

    final ObservableList<Quiz> data = FXCollections.observableArrayList();

    private static int storedSelectedQuizId;
    private static int storedQuizId;

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
    private TableColumn<Quiz, String> quizNameColumn;

    @FXML
    private TableView<Quiz> quizTableView;

    public EditQuizController() {
        data.addAll(quizService.findAllQuiz());
    }

    @FXML
    private void initialize() {

        // Setting data to right column "cellvalue"
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizName"));
        quizTableView.setItems(data);

        editButton.setOnAction(e -> {
            showEditQuizDialog();
        });

        // Delete quiz button
        deleteButton.setOnAction(e -> {
            handleDeleteQuiz();
        });

        quizTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Quiz>() {

            @Override
            public void changed(ObservableValue<? extends Quiz> observable, Quiz oldValue, Quiz newValue) {
                if(quizTableView.getSelectionModel().selectedItemProperty() != null) {
                    storedSelectedQuizId = quizTableView.getSelectionModel().getSelectedIndex();
                    storedQuizId = data.get(storedSelectedQuizId).getQuizId();
                    deleteButton.setDisable(false);
                } else {
                    deleteButton.setDisable(true);
                }
            }
        });
    }

    private void handleDeleteQuiz() {
        if(quizTableView.getSelectionModel().getSelectedItem() != null) {
            quizService.deleteQuiz(storedQuizId);
            System.out.println("Deleted stored quiz with id " + storedQuizId + " from database");

            int selectedQuiz = quizTableView.getSelectionModel().getSelectedIndex();
            quizTableView.getItems().remove(selectedQuiz);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inget valt");
            alert.setHeaderText("Ingen quiz är valt");
            alert.setContentText("För att ta bort, välj ett quiz");

            alert.showAndWait();
        }
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
