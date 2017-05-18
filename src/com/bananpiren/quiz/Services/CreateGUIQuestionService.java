package com.bananpiren.quiz.Services;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;

/**
 * Created by Anton on 2017-05-17.
 */
public class CreateGUIQuestionService {

    public VBox createSingleAnswerQuestion(String questionName, String answer1, String answer2, String answer3, String answer4){
        VBox questionBox = new VBox();
        final ToggleGroup radioGroup = new ToggleGroup();
        Label questionLabel = new Label(questionName);
        HBox answer1Box = new HBox();
        Label answer1Label = new Label(answer1);
        RadioButton answer1Button = new RadioButton();
        HBox answer2Box = new HBox();
        Label answer2Label = new Label(answer2);
        RadioButton answer2Button = new RadioButton();
        HBox answer3Box = new HBox();
        Label answer3Label = new Label(answer3);
        RadioButton answer3Button = new RadioButton();
        HBox answer4Box = new HBox();
        Label answer4Label = new Label(answer4);
        RadioButton answer4Button = new RadioButton();

        answer1Button.setToggleGroup(radioGroup);
        answer2Button.setToggleGroup(radioGroup);
        answer3Button.setToggleGroup(radioGroup);
        answer4Button.setToggleGroup(radioGroup);

        answer1Box.getChildren().addAll(answer1Label, answer1Button);
        answer1Box.setSpacing(5);
        answer2Box.getChildren().addAll(answer2Label, answer2Button);
        answer2Box.setSpacing(5);
        answer3Box.getChildren().addAll(answer3Label, answer3Button);
        answer3Box.setSpacing(5);
        answer4Box.getChildren().addAll(answer4Label, answer4Button);
        answer4Box.setSpacing(5);
        questionBox.getChildren().addAll(questionLabel, answer1Box, answer2Box, answer3Box, answer4Box);
        questionBox.setSpacing(5);

        return questionBox;
    }
    public VBox createMultipleChoiceQuestion(String questionName, String answer1, String answer2, String answer3, String answer4){
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
        answer2Box.getChildren().addAll(answer2Label,answer2CheckBox);
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
