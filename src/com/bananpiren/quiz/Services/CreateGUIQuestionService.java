package com.bananpiren.quiz.Services;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Anton on 2017-05-17.
 */
public class CreateGUIQuestionService {

    public VBox createSingleAnswerQuestion(){
        VBox questionBox = new VBox();
        final ToggleGroup radioGroup = new ToggleGroup();
        Label questionLabel = new Label();
        HBox answer1Box = new HBox();
        Label answer1Label = new Label();
        RadioButton answer1Button = new RadioButton();
        HBox answer2Box = new HBox();
        Label answer2Label = new Label();
        RadioButton answer2Button = new RadioButton();
        HBox answer3Box = new HBox();
        Label answer3Label = new Label();
        RadioButton answer3Button = new RadioButton();
        HBox answer4Box = new HBox();
        Label answer4Label = new Label();
        RadioButton answer4Button = new RadioButton();

        answer1Button.setToggleGroup(radioGroup);
        answer2Button.setToggleGroup(radioGroup);
        answer3Button.setToggleGroup(radioGroup);
        answer4Button.setToggleGroup(radioGroup);

        answer1Box.getChildren().addAll(answer1Label, answer1Button);
        answer2Box.getChildren().addAll(answer2Label, answer2Button);
        answer3Box.getChildren().addAll(answer3Label, answer3Button);
        answer4Box.getChildren().addAll(answer4Label, answer4Button);
        questionBox.getChildren().addAll(questionLabel, answer1Box, answer2Box, answer3Box, answer4Box);

        return questionBox;
    }

}
