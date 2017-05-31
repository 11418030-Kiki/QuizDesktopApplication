package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.CorrectQuiz;
import com.bananpiren.quiz.Entity.TakeQuiz;
import com.bananpiren.quiz.Entity.UserQuiz;
import com.bananpiren.quiz.Services.CorrectQuizService;
import com.bananpiren.quiz.Services.UserQuizService;
import com.bananpiren.quiz.java.model.QuizTimer;
import com.bananpiren.quiz.java.view.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
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
            sendUserQuiz();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("TakeQuizResult.fxml"));
                BorderPane takeQuizResult = loader.load();
                Main.mainLayout.setCenter(takeQuizResult);
            } catch (IOException f) {
                System.out.println("Couldn't load TakeQuiz.fxml: " + f);
            }
        });

        if (takeQuizList.get(0).getTimeLimit() != 0) {

            startQuizTimer();
        }
    }

    // store correct answer and useranswer
    private void sendQuiz() {
        int noMultiple = 0;
        int noSingle = 0;

        for (int i = 0; i < takeQuizList.size(); i++) {

            correctQuiz = new CorrectQuiz();
            correctQuiz.setAnswerId(Integer.parseInt(takeQuizList.get(i).getAnswerId()));
            correctQuiz.setCorrectAnswer(Integer.parseInt(takeQuizList.get(i).getCorrectAnswer()));
            correctQuiz.setUserId(LoginController.getCurrentUser().getUserId());

            // check if multi or single question
            if (takeQuizList.get(i).getQuestionType().equals("multiple")) {
                correctQuiz.setUserAnswer(multiAnswerList.get(i - noSingle).isSelected());
                noMultiple++;
            } else {
                correctQuiz.setUserAnswer(singleAnswerList.get(i - noMultiple).isSelected());
                noSingle++;
            }

            // create table
            CorrectQuizService correctQuizService = new CorrectQuizService();

            correctQuizService.correctQuiz(correctQuiz);
        }
    }

    // correct the quiz and save it in a new table
    private void sendUserQuiz() {
        UserQuiz userQuiz = new UserQuiz();
        userQuiz.setUserId(LoginController.getCurrentUser().getUserId());
        userQuiz.setQuizName(takeQuizList.get(0).getQuizName());
        userQuiz.setQuizId(takeQuizList.get(0).getQuizId());


        int points = 1;
        int countedPoints = 0;
        int questionId = Integer.parseInt(takeQuizList.get(0).getQuestionId());
        int newQuestion = 4;
        int noMultiple = 0;
        int noSingle = 0;

        // get points
        for (int i = 0; i < takeQuizList.size(); i++) {

            // kolla om det är ny fråga
            if (i % 4 == 0) {
                points = 0;
            }

            int selected = 0;

            // check if multi or single quiestion
            if (takeQuizList.get(i).getQuestionType().equals("multiple")) {
                selected = (multiAnswerList.get(i - noSingle).isSelected()) ? 1 : 0;
                noMultiple++;
            } else {
                selected = singleAnswerList.get(i - noMultiple).isSelected() ? 1 : 0;
                noSingle++;
            }

            if (selected == Integer.parseInt(takeQuizList.get(i).getCorrectAnswer())) {
                points++;
            }

            // om alla rätt så får du ett poäng annars inga
            if (points >= 4) {
                countedPoints++;
            }
        }

        int theResult = countedPoints;
        int questionNumber = takeQuizList.size() / 4;
        new Alert(Alert.AlertType.INFORMATION, "Du fick " + theResult + " poäng!" + " Maxpoäng är: " + questionNumber).showAndWait();

        userQuiz.setPoints(countedPoints);

        // create table
        UserQuizService userQuizService = new UserQuizService();

        userQuizService.userQuiz(userQuiz);

    }

    private void startQuizTimer() {
        QuizTimer.quizTimerClock(takeQuizList.get(0).getTimeLimit(), quizTimeLabel);
    }

    public void ternInQuiz() {
        //TODO: Skapa logik för att lämna in quiz.
        sendQuizButton.fire();
    }
}
