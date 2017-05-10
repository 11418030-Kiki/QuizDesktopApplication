package com.bananpiren.quiz.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

public class StatisticsController {

    // Dummydata
    ObservableList<PieChart.Data> userData = FXCollections.observableArrayList(
            new PieChart.Data("Rätt svar", 60),
            new PieChart.Data("Fel svar", 40)
    );

    @FXML
    private Tab correctAnswersTab;

    @FXML
    private PieChart correctAnswersPieChart;

    @FXML
    private Tab usersTab;

    @FXML
    private TableColumn<?, ?> quizTableView;

    @FXML
    private void initialize() {
        correctAnswersPieChart.setData(userData);
    }
}
