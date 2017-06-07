package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.UserQuiz;
import com.bananpiren.quiz.Services.UserQuizService;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.util.List;


public class TakeQuizResultController {
    @FXML
    private Text quizNameText;

    @FXML
    private Text correctQuizAnswerText;

    @FXML
    private TableView<Quiz> quizTableView;
    private UserQuizService userQuizService = new UserQuizService();

    @FXML
    private void initialize() {

        int quizID = StartController.getCurrentQuizId();
        List<UserQuiz> userQuizList = userQuizService.getAllUserQuizByQuizId(quizID);


        if(TakeQuizController.getShowCorrect()) {
            System.out.println("DETTA PROVET VISAR SJÄLVRÄTTNING");
        } else {
            System.out.println("DETTA VISAR INTE SJÄLVRÄTTNING!");
        }

        if (TakeQuizController.getSelfCorrect() && TakeQuizController.getShowCorrect()) {
            quizNameText.setText("" + userQuizList.get(userQuizList.size() - 1).getQuizName());

            correctQuizAnswerText.setText(
                    "Användare: " + userQuizList.get(userQuizList.size() - 1).getUserName() +
                            "\nQuiznamn: " + userQuizList.get(userQuizList.size() - 1).getQuizName() +
                            "\nAntal frågor: " + userQuizList.get(userQuizList.size() - 1).getNoOfQuestions() +
                            "\nMaxpoäng: " + userQuizList.get(userQuizList.size() - 1).getMaxPoints() +
                            "\nDin poäng: " + userQuizList.get(userQuizList.size() - 1).getPoints()
            );
        } else if (!TakeQuizController.getSelfCorrect()) {
            correctQuizAnswerText.setText("DETTA ÄR INTE SJÄLVRÄTTANDE!");

            // HÄR FÅ IN newCoolVBox!
            // så visar quizet
        } else if (TakeQuizController.getSelfCorrect() && !TakeQuizController.getShowCorrect()) {
            correctQuizAnswerText.setText("DETTA ÄR SJÄLVRÄTTANDE MEN VISAR INTE SJÄLVRÄTTNINGEN!");

        }
    }
}
