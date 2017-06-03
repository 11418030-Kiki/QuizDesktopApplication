package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.StatisticsUser;
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
    private TableColumn<StatisticsUser, String> statisticsUserCorrectPercentageColumn;

    @FXML
    private TableColumn<StatisticsUser, String> statisticsUserFirstNameColumn;

    @FXML
    private TableColumn<StatisticsUser, String> statisticsUserLastNameColumn;

    @FXML
    private TableView<StatisticsUser> statisticsUserTableViev;

    @FXML
    private PieChart correctAnswersPieChart;

    @FXML
    private Tab usersTab;

    @FXML
    private TableView<Quiz> quizTableView;

    @FXML
    private TableColumn<Quiz, String> quizTableColumn;

    private ObservableList<Quiz> data = FXCollections.observableArrayList();
    private ObservableList<StatisticsUser> statisticsUserData = FXCollections.observableArrayList();
    private QuizService quizService = new QuizService();
    private UserQuizService userQuizService = new UserQuizService();
    private int quizID;
    private int questionCount;
    private List<UserQuiz> userQuizList = new ArrayList<>();
    private double averageScore;
    private int users;
    private double userPoints;
    private double maxPoints;
    private double pointsPercentage;
    private String pointsPercentageString;

    @FXML
    private void initialize() {

        data.addAll(quizService.findAllQuiz());
        quizTableColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizName"));
        statisticsUserFirstNameColumn.setCellValueFactory(new PropertyValueFactory<StatisticsUser, String>("firstName"));
        statisticsUserLastNameColumn.setCellValueFactory(new PropertyValueFactory<StatisticsUser, String>("lastName"));
        statisticsUserCorrectPercentageColumn.setCellValueFactory(new PropertyValueFactory<StatisticsUser, String>("correctPercentage"));
        quizTableView.setItems(data);
        quizTableView.getSelectionModel().selectedItemProperty().addListener(e -> {
            statisticsUserData.clear();
            averageScore = 0;
            users = 0;
            questionCount = 0;
            quizID = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizId();
            questionCount = Integer.parseInt(QuestionService.getNumberOfQuestions(Integer.toString(quizID)));
            userQuizList = userQuizService.getAllUserQuizByQuizId(quizID);

            userQuizList.forEach(f -> {
                averageScore = averageScore + f.getPoints();
                users++;
            });

            averageScore = averageScore / users;
            double averageScorePercentage = (averageScore / questionCount) * 100;
            double wrongAnswerPercentage = 100 - averageScorePercentage;
            averageScorePercentage = Math.round(averageScorePercentage * 100.0) / 100.0;
            wrongAnswerPercentage = Math.round(wrongAnswerPercentage * 100.0) / 100.0;
            ObservableList<PieChart.Data> userData = FXCollections.observableArrayList(
                    new PieChart.Data("Rätt svar: " + averageScorePercentage + "%", averageScorePercentage),
                    new PieChart.Data("Fel svar: " + wrongAnswerPercentage + "%", wrongAnswerPercentage)
            );
            correctAnswersPieChart.setData(userData);

            userQuizList.forEach (y -> {
                userPoints = y.getPoints();
                maxPoints = y.getMaxPoints();
                pointsPercentage = (userPoints/maxPoints) * 100;
                pointsPercentage = Math.round(pointsPercentage * 100.0) / 100.0;
                pointsPercentageString = String.valueOf(pointsPercentage);
                statisticsUserData.add(
                        new StatisticsUser(y.getUserName(), y.getUserLastName(), pointsPercentageString));

            });
            statisticsUserTableViev.setItems(statisticsUserData);
        });


    }
}
