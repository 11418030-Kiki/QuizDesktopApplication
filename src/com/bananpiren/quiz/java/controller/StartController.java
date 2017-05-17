package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Services.FindQuizService;
import com.bananpiren.quiz.java.view.Main;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StartController {

    final ObservableList<Quiz> data = FXCollections.observableArrayList();

    private FindQuizService findQuizService = new FindQuizService();

    @FXML
    private TableColumn<Quiz, Integer> quizIdColumn;

    @FXML
    private TableColumn<Quiz, String> quizNameColumn;

    @FXML
    private TableView<Quiz> quizTableView;

    @FXML
    private TableColumn<Quiz, String> quizEndDateColumn;

    @FXML
    private Button takeQuizButton;

    public StartController() {
        data.addAll(findQuizService.findAllQuiz());
    }

    @FXML
    private void initialize() {
        // Setting data to right column "cellvalue"
        quizIdColumn.setCellValueFactory(new PropertyValueFactory<Quiz, Integer>("quizId"));
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizName"));
        quizEndDateColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizEndDate"));
        quizTableView.setItems(data);

        takeQuizButton.setDisable(true);
        quizTableView.getSelectionModel().selectedIndexProperty().addListener(y->{
            takeQuizButton.setDisable(false);
        });

        takeQuizButton.setOnAction((ActionEvent e) ->{

            int currentQuizId = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizId();



            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("TakeQuiz.fxml"));
                BorderPane takeQuiz = loader.load();
                Main.mainLayout.setCenter(takeQuiz);
            }catch(IOException f){
                System.out.println(f);
            }

        });
    }
}
