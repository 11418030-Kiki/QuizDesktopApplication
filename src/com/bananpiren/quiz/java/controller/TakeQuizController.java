package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.CorrectQuiz;
import com.bananpiren.quiz.Entity.TakeQuiz;
import com.bananpiren.quiz.Services.CorrectQuizService;
//import com.bananpiren.quiz.java.controller.LoginController;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;


public class TakeQuizController {

    @FXML
    private Label quizNameHeader;

    @FXML
    private Label quizLabel;

    @FXML
    private Button sendQuizButton;

    @FXML
    private CheckBox[] answerCheckbox;

    @FXML
    private CorrectQuiz correctQuiz;

    @FXML
    private ArrayList<TakeQuiz> takeQuizList = new ArrayList<>();

    @FXML
    private void initialize() {
        // get the takeQuizList
        takeQuizList = StartController.getTakeQuizList();

        // Create CorrectQuizObject
        sendQuizButton.setOnAction(event -> {
//correct();
            for (int i = 0; i < takeQuizList.size(); i++) {
                correctQuiz = new CorrectQuiz();

                correctQuiz.setAnswerId(Integer.parseInt(takeQuizList.get(i).getAnswerId()));
                correctQuiz.setCorrectAnswer(Integer.parseInt(takeQuizList.get(i).getCorrectAnswer()));

                // lägg i en metod och se om kommer åt
//                LoginController.getCurrentUser();
                // set the userAnswer
//                correctQuiz.setUserAnswer(answerCheckbox[i].isSelected());

                // create table
                CorrectQuizService correctQuizService = new CorrectQuizService();

                correctQuizService.correctQuiz(correctQuiz);
            }

        });
    }


    VBox createQuizQuestions(ArrayList<TakeQuiz> takeQuizList) {
        // length of the list divided with the number of questions
        int len = takeQuizList.size() / 4;

        String[] questionName = new String[len];
        Label[] questionLabel = new Label[len];
        String questionType = "";

        String[] answer = new String[takeQuizList.size()];
        Label[] answerLabel = new Label[takeQuizList.size()];
        answerCheckbox = new CheckBox[takeQuizList.size()];

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
            incQuest = i * answerNo;

            questionName[i] = takeQuizList.get(incQuest).getQuestion();
            questionType = takeQuizList.get(incQuest).getQuestionType();

            questionLabel[i] = new Label(questionName[i]);
            questionBox.getChildren().add(questionLabel[i]);
            questionBox.setSpacing(5);
            toggleGroups[i] = new ToggleGroup();
            Separator separator = new Separator();
            separator.setValignment(VPos.CENTER);

            // loop through the answers
            for (int j = 0; j < answerNo; j++) {
                incAnswer = incQuest + j;

                answer[j] = takeQuizList.get(incAnswer).getAnswer();

                answerLabel[j] = new Label(answer[j]);
                answerBox[j] = new HBox();

                answerBox[j].getChildren().add(answerLabel[j]);
                answerBox[j].setSpacing(5);

                // checks what kind of question
                if (questionType.equals("multiple")) {
                    answerCheckbox[j] = new CheckBox();
                    answerBox[j].getChildren().add(answerCheckbox[j]);
                } else {

                    answerButton[j] = new RadioButton();
                    answerButton[j].setToggleGroup(toggleGroups[i]);

                    answerBox[j].getChildren().add(answerButton[j]);
                }

                questionBox.getChildren().add(answerBox[j]);
            }
            questionBox.getChildren().add(separator);
        }

        return questionBox;
    }

}
