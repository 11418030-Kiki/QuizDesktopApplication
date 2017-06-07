package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.UserQuiz;
import com.bananpiren.quiz.Services.UserQuizService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2017-06-07.
 */
public class CorrectQuizController {

    @FXML
    private Button correctQuizButton;

    @FXML
    private TableView<UserQuiz> correctQuizTableView;

    @FXML
    private TableColumn<UserQuiz, String> quizNameColumn;

    @FXML
    private TableColumn<UserQuiz, String> userNameColumn;

    private ObservableList<UserQuiz> userQuizData = FXCollections.observableArrayList();
    private List<UserQuiz> userQuizList = new ArrayList<>();
    private UserQuizService userQuizService = new UserQuizService();

    @FXML
    private void initialize(){
        userQuizList.addAll(userQuizService.getAllUserQuiz());
        userQuizList.forEach(e->{
            if(e.getPoints()<-1){
                userQuizData.add(e);
            }
        });

        quizNameColumn.setCellValueFactory(new PropertyValueFactory<UserQuiz, String>("QuizName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<UserQuiz, String>("userName"));

    }
}
