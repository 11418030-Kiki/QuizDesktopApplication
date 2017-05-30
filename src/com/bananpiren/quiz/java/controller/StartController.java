package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.TakeQuiz;
import com.bananpiren.quiz.Services.QuizService;
import com.bananpiren.quiz.java.view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.swing.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StartController {

    private ObservableList<Quiz> data = FXCollections.observableArrayList();
    //    private ObservableList<VBox> vbox = FXCollections.observableArrayList();
    private static ArrayList<TakeQuiz> takeQuizList = new ArrayList<>();

    private VBox vbox;

    private QuizService quizService = new QuizService();

    public int currentQuizId;

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
        data.addAll(quizService.findAllQuiz());
    }

    @FXML
    private void initialize() {
        // Setting data to right column "cellvalue"
        quizIdColumn.setCellValueFactory(new PropertyValueFactory<Quiz, Integer>("quizId"));
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizName"));
        quizEndDateColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizEndDate"));
        quizTableView.setItems(data);

        takeQuizButton.setDisable(true);
        quizTableView.getSelectionModel().selectedIndexProperty().addListener(y -> {
            takeQuizButton.setDisable(false);
        });


        takeQuizButton.setOnAction((ActionEvent e) -> {
            String quizEndDate = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizEndDate();
            String quizStartDate = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizStartDate();
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateQuizEndDate = inputFormat.parse(quizEndDate);
                Date dateQuizStartDate = inputFormat.parse(quizStartDate);
                Calendar calQuizEndDate = Calendar.getInstance();
                Calendar calquizStartDate = Calendar.getInstance();
                calQuizEndDate.setTime(dateQuizEndDate);
                calquizStartDate.setTime(dateQuizStartDate);
                Calendar calToday = Calendar.getInstance();
                calToday.set(Calendar.HOUR_OF_DAY, 0);
                calToday.set(Calendar.MINUTE, 0);
                calToday.set(Calendar.SECOND, 0);
                calToday.set(Calendar.MILLISECOND, 0);
                if (calQuizEndDate.compareTo(calToday) <= 0) {
                    Alert toLate = new Alert(Alert.AlertType.ERROR);
                    toLate.setTitle("Meddelande");
                    toLate.setHeaderText("Datumet passerat");
                    toLate.setContentText("Datumet för provet har redan passerat");
                    toLate.showAndWait();
                }else if(calquizStartDate.compareTo(calToday) >=0){
                    Alert toEarly = new Alert(Alert.AlertType.INFORMATION);
                    toEarly.setTitle("Meddelande");
                    toEarly.setHeaderText("Quizet har inte startat");
                    toEarly.setContentText("Quizets startdatum har inte inträffat");
                    toEarly.showAndWait();
                } else {

                    currentQuizId = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizId();

                    VBox newCoolVbox = new VBox();

                    TakeQuizController takeQuizController = new TakeQuizController();

                    // get the list of the current quiz
                    takeQuizList = quizService.currentQuiz(currentQuizId);

                    // displaying the question and answers on vbox
                    vbox = takeQuizController.createQuizQuestions(takeQuizList);
                    newCoolVbox.getChildren().addAll(vbox);

                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Main.class.getResource("TakeQuiz.fxml"));
                        BorderPane takeQuiz = loader.load();
                        takeQuiz.setCenter(newCoolVbox);
                        Main.mainLayout.setCenter(takeQuiz);
                    } catch (IOException f) {
                        System.out.println("Couldn't load TakeQuiz.fxml: " + f);
                    }
                }
            } catch (Exception f) {
                System.out.println(f);
            }

        });
    }


    static ArrayList<TakeQuiz> getTakeQuizList() {
        return takeQuizList;
    }

}
