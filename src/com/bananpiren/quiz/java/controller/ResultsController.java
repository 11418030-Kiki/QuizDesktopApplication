package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.User;
import com.bananpiren.quiz.Entity.UserQuiz;
import com.bananpiren.quiz.Services.UserQuizService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ResultsController {

    @FXML
    private TableView resultsTable;

    @FXML
    private TextArea resultsTextArea;

    @FXML
    private TableColumn quizNameColumn;

    @FXML
    private TableColumn correctAnswersTable;

    @FXML
    private TableColumn questionCountColumn;

    @FXML
    private TableColumn dateColumn;

    final ObservableList<User> data = FXCollections.observableArrayList();

    private void initialize(){}
}
