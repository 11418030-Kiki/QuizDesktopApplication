package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.TakeQuiz;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class TakeQuizController {

    private HBox[] answerBox;
    private Label[] answerLabel;
    private RadioButton[] answerButton;
    private CheckBox[] answerCheckBox;

    // skapa så loopar igenom allting
    VBox createSingleAnswerQuestion(String questionName, String answer1, String answer2, String answer3, String answer4) {
        VBox questionBox = new VBox();
        ToggleGroup radioGroup = new ToggleGroup();
        Label questionLabel = new Label(questionName);

        String[] answers = new String[4];
        for (int i = 0; i < 4; i++) {
            answers[i] = "answer" + i;
        }

        for (int i = 0; i < 4; i++) {
            System.out.println("answers[i] = " + answers[i]);
        }

        answerBox = new HBox[4];
        answerLabel = new Label[4];
        answerButton = new RadioButton[4];

        for (int i = 0; i < 4; i++) {

        }


//        Label answer1Label = new Label(answer1);
//        RadioButton answer1Button = new RadioButton();
//
//        HBox answer2Box = new HBox();
//        Label answer2Label = new Label(answer2);
//        RadioButton answer2Button = new RadioButton();
//        HBox answer3Box = new HBox();
//        Label answer3Label = new Label(answer3);
//        RadioButton answer3Button = new RadioButton();
//        HBox answer4Box = new HBox();
//        Label answer4Label = new Label(answer4);
//        RadioButton answer4Button = new RadioButton();
//
//        answer1Button.setToggleGroup(radioGroup);
//
//        answer2Button.setToggleGroup(radioGroup);
//        answer3Button.setToggleGroup(radioGroup);
//        answer4Button.setToggleGroup(radioGroup);
//
//        answer1Box.getChildren().addAll(answer1Label, answer1Button);
//        answer1Box.setSpacing(5);
//
//        answer2Box.getChildren().addAll(answer2Label, answer2Button);
//        answer2Box.setSpacing(5);
//        answer3Box.getChildren().addAll(answer3Label, answer3Button);
//        answer3Box.setSpacing(5);
//        answer4Box.getChildren().addAll(answer4Label, answer4Button);
//        answer4Box.setSpacing(5);
//        questionBox.getChildren().addAll(questionLabel, answer1Box, answer2Box, answer3Box, answer4Box);
//        questionBox.setSpacing(5);

        return questionBox;
    }

    VBox createMultipleChoiceQuestion(ArrayList<TakeQuiz> takeQuizList) {
        String questionName = "";
        String answer1 = "";
        String answer2 = "";
        String answer3 = "";
        String answer4 = "";

        // här är en snabblösning bara för att jag ska synka nu
        // ska bygga om detta så det blir mer dynamiskt
        for (TakeQuiz tq : takeQuizList) {
            questionName = takeQuizList.get(0).getQuestion();
            answer1 = takeQuizList.get(0).getAnswer();
            answer2 = takeQuizList.get(1).getAnswer();
            answer3 = takeQuizList.get(2).getAnswer();
            answer4 = takeQuizList.get(3).getAnswer();

        }


        VBox questionBox = new VBox();
        Label questionLabel = new Label(questionName);
        HBox answer1Box = new HBox();
        Label answer1Label = new Label(answer1);
        CheckBox answer1CheckBox = new CheckBox();
        HBox answer2Box = new HBox();
        Label answer2Label = new Label(answer2);
        CheckBox answer2CheckBox = new CheckBox();
        HBox answer3Box = new HBox();
        Label answer3Label = new Label(answer3);
        CheckBox answer3CheckBox = new CheckBox();
        HBox answer4Box = new HBox();
        Label answer4Label = new Label(answer4);
        CheckBox answer4CheckBox = new CheckBox();

        answer1Box.getChildren().addAll(answer1Label, answer1CheckBox);
        answer1Box.setSpacing(5);
        answer2Box.getChildren().addAll(answer2Label, answer2CheckBox);
        answer2Box.setSpacing(5);
        answer3Box.getChildren().addAll(answer3Label, answer3CheckBox);
        answer3Box.setSpacing(5);
        answer4Box.getChildren().addAll(answer4Label, answer4CheckBox);
        answer4Box.setSpacing(5);
        questionBox.getChildren().addAll(questionLabel, answer1Box, answer2Box, answer3Box, answer4Box);
        questionBox.setSpacing(5);


        return questionBox;
    }

}
