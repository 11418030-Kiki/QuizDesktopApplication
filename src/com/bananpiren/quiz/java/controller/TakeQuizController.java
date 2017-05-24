package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.TakeQuiz;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class TakeQuizController {

    @FXML
    private Label quizNameHeader;

    VBox createQuizQuestions(ArrayList<TakeQuiz> takeQuizList) {
        // length of the list divided with the number of questions
        int len = takeQuizList.size()/4;

        String[] questionName = new String[len];
        Label[] questionLabel = new Label[len];
        String questionType = "";

        String[] answer = new String[takeQuizList.size()];
        Label[] answerLabel = new Label[takeQuizList.size()];
        CheckBox[] answerCheckbox = new CheckBox[takeQuizList.size()];

        RadioButton[] answerButton = new RadioButton[takeQuizList.size()];
        ToggleGroup[] radioGroup = new ToggleGroup[10]; // ett godtyckligt antal grupper

        HBox[] answerBox = new HBox[takeQuizList.size()];


        VBox questionBox = new VBox();

        int incQuest = 0;
        int incAnswer = 0;

        // loop through the questions
        for (int i = 0; i < len; i++) {
            // increment the question with the number of answers and get the question
            incQuest = i * 4;

            questionName[i] = takeQuizList.get(incQuest).getQuestion();
            questionType = takeQuizList.get(incQuest).getQuestionType();

            questionLabel[i] = new Label(questionName[i]);
            questionBox.getChildren().add(questionLabel[i]);
            questionBox.setSpacing(5);

            // loop through the answers
            for (int j = 0; j < 4; j++) {
                incAnswer = incQuest + j;

                answer[j] = takeQuizList.get(incAnswer).getAnswer();

                answerLabel[j] = new Label(answer[j]);
                answerBox[j] = new HBox();

                answerBox[j].getChildren().add(answerLabel[j]);
                answerBox[j].setSpacing(5);

                // checks what kind of question
                if(questionType.equals("multiple")) {
                    answerCheckbox[j] = new CheckBox();
                    answerBox[j].getChildren().add(answerCheckbox[j]);
                } else {
                    answerButton[j] = new RadioButton();
                    answerBox[j].getChildren().add(answerButton[j]);
                }

                questionBox.getChildren().add(answerBox[j]);
            }
        }

        return questionBox;
    }

}
