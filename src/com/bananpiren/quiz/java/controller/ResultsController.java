package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.QuizQuestions;
import com.bananpiren.quiz.Entity.UserQuiz;
import com.bananpiren.quiz.Services.QuestionService;
import com.bananpiren.quiz.Services.QuizService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultsController {

    @FXML
    private TableView<UserQuiz> resultsTable;

    @FXML
    private TextArea resultsTextArea;

    @FXML
    private TableColumn<UserQuiz, Integer> quizNameColumn;

    @FXML
    private TableColumn<UserQuiz, Integer> correctAnswersColumn;

    @FXML
    private PieChart resultPieChart;

    private final ObservableList<UserQuiz> data = FXCollections.observableArrayList();

    private static int storedSelectedTableIndex;
    private int storedScore;
    private int storedWrongAnswers;
    private String storedNumberOfQuestions;
    private String storedSelectedQuizId;

    public ResultsController() {
        data.addAll(QuizService.getUserQuiz(LoginController.getCurrentUser().getUserId()));
    }

    @FXML
    private void initialize(){
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<UserQuiz, Integer>("QuizName"));
        correctAnswersColumn.setCellValueFactory(new PropertyValueFactory<UserQuiz, Integer>("points"));
        resultsTable.setItems(data);

        resultsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserQuiz>() {
            @Override
            public void changed(ObservableValue<? extends UserQuiz> observable, UserQuiz oldValue, UserQuiz newValue) {
                storedSelectedTableIndex = resultsTable.getSelectionModel().getSelectedIndex();

                storedScore = data.get(storedSelectedTableIndex).getPoints();
                storedSelectedQuizId = data.get(storedSelectedTableIndex).getQuizId();
                storedNumberOfQuestions = QuestionService.getNumberOfQuestions(storedSelectedQuizId);
                storedWrongAnswers = storedScore - Integer.valueOf(storedNumberOfQuestions);


                ObservableList<PieChart.Data> userData = FXCollections.observableArrayList(
                        new PieChart.Data("Rätt svar: " + storedScore, storedScore),
                        new PieChart.Data("Fel svar: " + storedWrongAnswers, storedWrongAnswers)
                );
                resultPieChart.setData(userData);
            }
        });
    }
}
