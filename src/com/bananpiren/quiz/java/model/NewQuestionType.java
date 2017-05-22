//TODO: Move to model folder

package com.bananpiren.quiz.java.model;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class NewQuestionType {

    public static int questionNumber = 1;
    public static int answerNumber = 1;

    public Label label;
    VBox questionField;
    public TextField questionTextField;
    public TextField[] newAnswerTextField;
    public CheckBox[] answerCheckbox;
    public RadioButton[] radioButtonAnswer;
    public HBox[] hBox;

    public NewQuestionType(ListView<Pane> List) {
        questionField = new VBox();
        questionField.setPrefHeight(150);

        label = new Label();
        questionField.getChildren().add(label);

        questionTextField = new TextField("Fråga " + questionNumber);
        questionField.getChildren().add(questionTextField);

        List.getItems().add(questionField);
        List.scrollTo(List.getItems().size() - 1);
    }

    public void multipleAnswer() {
        label.setText("Flervalsfråga");

        newAnswerTextField = new TextField[4];
        answerCheckbox = new CheckBox[4];
        hBox = new HBox[4];

        for(int i = 0; i < 4; i++){
            hBox[i] = new HBox();
            Button deleteButton = new Button("X");
            newAnswerTextField[i] = new TextField();
            newAnswerTextField[i].setText("Fråga " + questionNumber + " svar " + answerNumber++);
            hBox[i].getChildren().addAll(deleteButton, newAnswerTextField[i]);

            answerCheckbox[i] = new CheckBox("Rätt svar");
            hBox[i].getChildren().add(answerCheckbox[i]);

            questionField.getChildren().add(hBox[i]);
        }
        answerNumber = 1;
        questionNumber++;
    }

    public void singleAnswer() {
        label.setText("Fråga med ett svar");

        newAnswerTextField = new TextField[4];
        answerCheckbox = new CheckBox[4];
        ToggleGroup answerToggleGroup = new ToggleGroup();
        radioButtonAnswer = new RadioButton[4];
        hBox = new HBox[4];

        for(int i = 0; i < 4; i++){
            hBox[i] = new HBox();
            Button deleteButton = new Button("X");
            newAnswerTextField[i] = new TextField();
            newAnswerTextField[i].setText("Fråga " + questionNumber + " svar " + answerNumber++);
            hBox[i].getChildren().addAll(deleteButton, newAnswerTextField[i]);

            radioButtonAnswer[i] = new RadioButton("Rätt svar");
            radioButtonAnswer[i].setToggleGroup(answerToggleGroup);
            hBox[i].getChildren().add(radioButtonAnswer[i]);

            questionField.getChildren().add(hBox[i]);
        }
        answerNumber = 1;
        questionNumber++;
    }
}