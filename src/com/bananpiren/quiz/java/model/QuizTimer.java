package com.bananpiren.quiz.java.model;

import com.bananpiren.quiz.java.controller.TakeQuizController;
import javafx.application.Platform;
import javafx.scene.control.*;

public class QuizTimer {


    public static void quizTimerClock(int quizTime, Label quizTimeLabel) {
        TakeQuizController takeQuizController = new TakeQuizController();

        Runnable updateQuizTimeRunnable = () -> {
            int timeMin = quizTime;
            int timeSek = 60;

            for (int i = 0; quizTime >= i; i++) {
                int finalTimeMin = timeMin;
                if (finalTimeMin > 1) {
                    Platform.runLater(() -> {
                        quizTimeLabel.setText(finalTimeMin + " :min");
                    });
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeMin--;
                }
            }

            for (int o = 0; timeSek > o; o++){
                int finalTimeSek = timeSek;
                if (timeMin == 1){
                    Platform.runLater(() -> {
                        quizTimeLabel.setText(finalTimeSek + " :sek");
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    timeSek--;
                }
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
