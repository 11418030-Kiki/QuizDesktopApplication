package com.bananpiren.quiz.java.model;

import com.bananpiren.quiz.java.controller.TakeQuizController;
import javafx.application.Platform;
import javafx.scene.control.*;

public class QuizTimer {


    public static void quizTimerClock(int quizTime, Label quizTimeLabel) {
        TakeQuizController takeQuizController = new TakeQuizController();

        Runnable updateQuizTimeRunnable = () -> {
            int time = quizTime;

            for (int i = 0; quizTime >= i; i++) {
                int finalTime = time;
                Platform.runLater(() -> {
                    quizTimeLabel.setText(finalTime + "");
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time--;
            }
            Platform.runLater(() -> {
                quizTimeLabel.setText("Times up!");
                takeQuizController.ternInQuiz();
            });
        };

        Thread updateQuizTimeThread = new Thread(updateQuizTimeRunnable);
        updateQuizTimeThread.start();

    }
}
