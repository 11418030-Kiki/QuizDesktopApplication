package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.StatisticCurrentUser;
import com.bananpiren.quiz.Entity.StatisticsUser;
import com.bananpiren.quiz.Entity.UserQuiz;
import com.bananpiren.quiz.Services.QuestionService;
import com.bananpiren.quiz.Services.QuizService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ResultsController {

    @FXML
    private TableView<StatisticCurrentUser> resultsTable;

    @FXML
    private TableColumn<StatisticCurrentUser, Integer> quizNameColumn;

    @FXML
    private TableColumn<StatisticCurrentUser, Integer> correctAnswersColumn;

    @FXML
    private TableColumn<StatisticCurrentUser, Integer> numberOfQuestionsColumn;

    @FXML
    private TableColumn<StatisticCurrentUser, String> resultColumn;

    @FXML
    private TableColumn<StatisticCurrentUser, String> gradeColumn;

    @FXML
    private PieChart resultPieChart;

    private ObservableList<UserQuiz> data = FXCollections.observableArrayList();
    private ObservableList<StatisticsUser> userData = FXCollections.observableArrayList();
    private ObservableList<StatisticCurrentUser> statisticsUserData = FXCollections.observableArrayList();

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
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<>("QuizName"));
        correctAnswersColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        numberOfQuestionsColumn.setCellValueFactory(new PropertyValueFactory<>("noOfQuestions"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("correctPercentage"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        data.forEach(y -> {
            double userPoints = y.getPoints();
            double maxPoints = y.getMaxPoints();
            double pointsPercentage = (userPoints/maxPoints) * 100;
            pointsPercentage = Math.round(pointsPercentage * 100.0) / 100.0;
            String pointsPercentageString = String.valueOf(pointsPercentage);
            userData.add(
                    new StatisticsUser(y.getUserName(), y.getUserLastName(), pointsPercentageString, y.getUserId()));
        });

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getPoints() != -1) {
                statisticsUserData.add(new StatisticCurrentUser(
                        data.get(i).getQuizName(),
                        data.get(i).getPoints(),
                        data.get(i).getNoOfQuestions(),
                        userData.get(i).getCorrectPercentage(),
                        userData.get(i).getGrade())
                );
            }
        }

        resultsTable.setItems(statisticsUserData);

        resultsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            storedSelectedTableIndex = resultsTable.getSelectionModel().getSelectedIndex();

            storedScore = statisticsUserData.get(storedSelectedTableIndex).getPoints();
            storedSelectedQuizId = data.get(storedSelectedTableIndex).getQuizId();
            storedNumberOfQuestions = QuestionService.getNumberOfQuestions(storedSelectedQuizId);
            storedWrongAnswers = storedScore - Integer.valueOf(storedNumberOfQuestions);

            ObservableList<PieChart.Data> userData = FXCollections.observableArrayList(
                    new PieChart.Data("Rätt svar: " + storedScore, storedScore),
                    new PieChart.Data("Fel svar: " + storedWrongAnswers, storedWrongAnswers)
            );
            resultPieChart.setData(userData);
        });
    }
}
