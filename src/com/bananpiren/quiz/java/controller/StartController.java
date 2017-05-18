package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Services.CreateGUIQuestionService;
import com.bananpiren.quiz.Services.FindQuizService;
import com.bananpiren.quiz.java.view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StartController {

    final ObservableList<Quiz> data = FXCollections.observableArrayList();

    private FindQuizService findQuizService = new FindQuizService();
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

             currentQuizId = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizId();

            CreateGUIQuestionService QuestionService = new CreateGUIQuestionService ();
            VBox newCoolVbox = new VBox();
            VBox vBox1 = QuestionService.createSingleAnswerQuestion("Vad är en heffaklump?", "En elefant", "En systemutvecklare på speed", "En SD-röstare", "Venne?");
            VBox vBox2 = QuestionService.createMultipleChoiceQuestion("Vad innehåller bröd?", "Vatten", "Vetemjöl", "Fisar", "Sten");
            newCoolVbox.getChildren().addAll(vBox1, vBox2);

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("TakeQuiz.fxml"));
                BorderPane takeQuiz = loader.load();
                takeQuiz.setCenter(newCoolVbox);
                Main.mainLayout.setCenter(takeQuiz);
            }catch(IOException f){
                System.out.println("Couldn't load TakeQuiz.fxml: "+ f);
            }



        });
    }
}
