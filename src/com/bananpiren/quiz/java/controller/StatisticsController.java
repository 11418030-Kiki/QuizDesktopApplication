package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.UserQuiz;
import com.bananpiren.quiz.Services.QuestionService;
import com.bananpiren.quiz.Services.QuizService;
import com.bananpiren.quiz.Services.UserQuizService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class StatisticsController {

    @FXML
    private Tab correctAnswersTab;

    @FXML
    private PieChart correctAnswersPieChart;

    @FXML
    private Tab usersTab;

    @FXML
    private TableView<Quiz> quizTableView;

    @FXML
    private TableColumn<Quiz, String> quizTableColumn;

    private ObservableList<Quiz> data = FXCollections.observableArrayList();
    private QuizService quizService = new QuizService();
    private UserQuizService userQuizService = new UserQuizService();
    private int quizID;
    private int questionCount;
    private List<UserQuiz> userQuizList = new ArrayList<>();
    private double averageScore;
    private int users;

    @FXML
    private void initialize() {

        data.addAll(quizService.findAllQuiz());
        quizTableColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizName"));
        quizTableView.setItems(data);
        quizTableView.getSelectionModel().selectedItemProperty().addListener(e -> {
            averageScore = 0;
            users = 0;
            questionCount = 0;
            quizID = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizId();
            questionCount = Integer.parseInt(QuestionService.getNumberOfQuestions(Integer.toString(quizID)));
            userQuizList = userQuizService.getAllUserQuizById(quizID);

            userQuizList.forEach(f -> {
                averageScore = averageScore + f.getPoints();
                users++;
            });

            averageScore = averageScore / users;
            double averageScorePercentage = (averageScore / questionCount) * 100;
            double wrongAnswerPercentage = 100 - averageScorePercentage;

            ObservableList<PieChart.Data> userData = FXCollections.observableArrayList(
                    new PieChart.Data("Rätt svar: " + averageScorePercentage + "%", averageScorePercentage),
                    new PieChart.Data("Fel svar: " + wrongAnswerPercentage + "%", wrongAnswerPercentage)
            );
            correctAnswersPieChart.setData(userData);
        });


    }
}
