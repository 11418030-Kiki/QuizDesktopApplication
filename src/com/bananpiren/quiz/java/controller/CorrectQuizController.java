package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.TakeQuiz;
import com.bananpiren.quiz.Entity.UserQuiz;
import com.bananpiren.quiz.Services.UserQuizService;
import com.bananpiren.quiz.java.view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2017-06-07.
 */
public class CorrectQuizController {

    // private ArrayList<TakeQuiz> takeQuizList = new ArrayList<>();

    @FXML
    private Button correctQuizButton;

    @FXML
    private TableView<UserQuiz> correctQuizTableView;

    @FXML
    private TableColumn<UserQuiz, String> quizNameColumn;

    @FXML
    private TableColumn<UserQuiz, String> userNameColumn;
    private ArrayList<TakeQuiz> takeQuizList;
    private ObservableList<UserQuiz> userQuizData = FXCollections.observableArrayList();
    private List<UserQuiz> userQuizList = new ArrayList<>();
    private UserQuizService userQuizService = new UserQuizService();

    @FXML
    private void initialize (){
       takeQuizList = StartController.getTakeQuizList();

        userQuizList.addAll(userQuizService.getAllUserQuiz());
        userQuizList.forEach(e->{
            if(e.getPoints()>0){
                userQuizData.add(e);
            }
        });

        quizNameColumn.setCellValueFactory(new PropertyValueFactory<UserQuiz, String>("QuizName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<UserQuiz, String>("userName"));
        correctQuizTableView.setItems(userQuizData);

        correctQuizButton.setDisable(true);

        correctQuizTableView.getSelectionModel().selectedItemProperty().addListener(e->{
            correctQuizButton.setDisable(false);
        });

        correctQuizButton.setOnAction(e->{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("CorrectQuizEdit.fxml"));
                BorderPane correctQuiz = loader.load();
                correctQuiz.setCenter(createQuizQuestions());
                Main.mainLayout.setCenter(correctQuiz);
            } catch (IOException f) {
                System.out.println("Couldn't load CorrectQuizEditController.fxml: " + f);
            }
        });
    }

    private VBox createQuizQuestions() {

        int openQuestions = 0;

        // reset the lists
//        multiAnswerList.clear();
 //       singleAnswerList.clear();
  //      openAnswerList.clear();

        // check the number of open questions
        for (TakeQuiz a : takeQuizList) {
            if (a.getQuestionType().equals("open")) {
                openQuestions++;
            }
        }

        // length of the list divided with the number of questions plus number of open questions
        int len = (takeQuizList.size() / 4) + openQuestions;


        String[] questionName = new String[len];
        Label[] questionLabel = new Label[len];
        String questionType = "";

        String[] answer = new String[takeQuizList.size()];
        Label[] answerLabel = new Label[takeQuizList.size()];
        CheckBox[] answerCheckbox = new CheckBox[takeQuizList.size()];
        TextField[] answerTextField = new TextField[takeQuizList.size()];

        RadioButton[] answerButton = new RadioButton[takeQuizList.size()];

        ToggleGroup[] toggleGroups = new ToggleGroup[len]; // set with the number of questions

        HBox[] answerBox = new HBox[takeQuizList.size()];

        VBox questionBox = new VBox();

        int incQuest = 0;
        int incAnswer = 0;
        int answerNo = 4;

        // loop through the questions
        for (int i = 0; i < len; i++) {
            // increment the question with the number of answers and get the question
            questionName[i] = takeQuizList.get(incQuest).getQuestion();
            questionType = takeQuizList.get(incQuest).getQuestionType();

            questionLabel[i] = new Label(questionName[i]);
            questionBox.getChildren().add(questionLabel[i]);
            questionBox.setSpacing(5);
            toggleGroups[i] = new ToggleGroup();
            Separator separator = new Separator();
            separator.setValignment(VPos.CENTER);

            if (questionType.equals("multiple") || questionType.equals("single")) {
                answerNo = 4;
            } else if (questionType.equals("open")) {
                answerNo = 1;
            }

            // loop through the answers
            for (int j = 0; j < answerNo; j++) {
                incAnswer = incQuest + j;

                answer[j] = takeQuizList.get(incAnswer).getAnswer();

                answerLabel[j] = new Label(answer[j]);
                answerBox[j] = new HBox();

                answerBox[j].setSpacing(5);

                // checks what kind of question
                if (questionType.equals("multiple")) {
                    answerCheckbox[j] = new CheckBox();
                    answerBox[j].getChildren().add(answerCheckbox[j]);
                    answerBox[j].getChildren().add(answerLabel[j]);

                 //   multiAnswerList.add(answerCheckbox[j]);

                } else if (questionType.equals("single")) {
                    answerButton[j] = new RadioButton();
                    answerButton[j].setToggleGroup(toggleGroups[i]);

                    answerBox[j].getChildren().add(answerButton[j]);
                    answerBox[j].getChildren().add(answerLabel[j]);

                //    singleAnswerList.add(answerButton[j]);

                } else if (questionType.equals("open")) {
                    answerTextField[j] = new TextField();

                    answerBox[j].getChildren().add(answerTextField[j]);
//                    answerBox[j].getChildren().add(answerLabel[j]);

                //    openAnswerList.add(answerTextField[j]);
                }

                questionBox.getChildren().add(answerBox[j]);
            }
            questionBox.getChildren().add(separator);

            // increment with the number of answers
            incQuest += answerNo;
        }

        return questionBox;
    }
}
