package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.TakeQuiz;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;
import java.util.ArrayList;


public class TakeQuizController {

    @FXML
    private Label quizNameHeader;

    @FXML
    private Label quizLabel;

    VBox createQuizQuestions(ArrayList<TakeQuiz> takeQuizList) {
        // length of the list divided with the number of questions
        int len = takeQuizList.size()/4;

        String[] questionName = new String[len];
        Label[] questionLabel = new Label[len];
        String questionType = "";

        String[] answer = new String[takeQuizList.size()];
        Label[] answerLabel = new Label[takeQuizList.size()];
        CheckBox[] answerCheckbox = new CheckBox[takeQuizList.size()];

        TextArea[] answerTextArea = new TextArea[len];

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
                if(questionType.equals("multiple")) {
                    answerCheckbox[j] = new CheckBox();
                    answerBox[j].getChildren().add(answerCheckbox[j]);
                } else if(questionType.equals("open")){
                    answerTextArea[i] = new TextArea();
                    answerBox[i].getChildren().add(answerTextArea[i]);
                } else {
                    answerButton[j] = new RadioButton();
                    answerButton[j].setToggleGroup(toggleGroups[i]);

                    answerBox[j].getChildren().add(answerButton[j]);
                }
//                toggleGroups = new ToggleGroup(); Lägg till denna
//                // if you are on the last run with radiobuttons
//                if(j == answerNo-1 && !questionType.equals("multiple")) {
//                    answer1Button.setToggleGroup(radioGroup);
//                    answer2Button.setToggleGroup(radioGroup);
//                    answer3Button.setToggleGroup(radioGroup);
//                    answer4Button.setToggleGroup(radioGroup);
//                }

                questionBox.getChildren().add(answerBox[j]);
            }
            questionBox.getChildren().add(separator);
        }

        return questionBox;
    }
}
