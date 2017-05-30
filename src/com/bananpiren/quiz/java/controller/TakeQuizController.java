package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.CorrectQuiz;
import com.bananpiren.quiz.Entity.TakeQuiz;
import com.bananpiren.quiz.Services.CorrectQuizService;
import com.bananpiren.quiz.java.model.QuizTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;


public class TakeQuizController {
    @FXML
    private Label quizNameHeader;

    @FXML
    private Label quizLabel;

    @FXML
    private Button sendQuizButton;

    @FXML
    private ArrayList<CheckBox> multiAnswerList = new ArrayList<>();

    @FXML
    private
    ArrayList<RadioButton> singleAnswerList = new ArrayList<>();

    @FXML
    private
    ArrayList<TakeQuiz> takeQuizList = new ArrayList<>();


    @FXML
    private CorrectQuiz correctQuiz;



//    public TakeQuizController() {
//
//    }


    @FXML
    private Label quizTimeLabel;

    @FXML
    private void initialize() {
        takeQuizList = StartController.getTakeQuizList();
        multiAnswerList = StartController.getMultiAnswerList();
        singleAnswerList = StartController.getSingleAnswerList();

        // Create CorrectQuizObject
        sendQuizButton.setOnAction(event -> {
            sendQuiz();
        });

        updateQuizTimer();
    }

    // kolla med konstruktorn om det går föra över allt i konstruktorn istället

    private void sendQuiz() {

        for (int i = 0; i < takeQuizList.size(); i++) {

            correctQuiz = new CorrectQuiz();

            correctQuiz.setAnswerId(Integer.parseInt(takeQuizList.get(i).getAnswerId()));
            correctQuiz.setCorrectAnswer(Integer.parseInt(takeQuizList.get(i).getCorrectAnswer()));
            correctQuiz.setUserId(LoginController.getCurrentUser().getUserId());


            correctQuiz.setUserAnswer(multiAnswerList.get(i).isSelected());

            // create table
            CorrectQuizService correctQuizService = new CorrectQuizService();

            correctQuizService.correctQuiz(correctQuiz);
        }
    }

    private void updateQuizTimer() {
        //TODO: Stoppa in current quiz time istället för fast värde under
        QuizTimer.quizTimerClock(3, quizTimeLabel);
    }

    public void ternInQuiz() {
        //TODO: Skapa logik för att lämna in quiz.
//        sendQuizButton.fire();
    }


}
