package com.bananpiren.quiz.java.model;

import com.bananpiren.quiz.java.controller.TakeQuizController;
import javafx.application.Platform;
import javafx.scene.control.*;

public class QuizTimer {


    public static void quizTimerClock(int quizTime, Label quizTimeLabel) {
        TakeQuizController takeQuizController = new TakeQuizController();

        Runnable updateQuizTimeRunnable = () -> {
            int timeMin = quizTime -1;
            int timeSek = 59;

            while (timeMin > 0 || timeSek > 0){
                int finalTimeMin = timeMin;
                int finalTimeSek = timeSek;
                    Platform.runLater(() -> {
                        quizTimeLabel.setText(finalTimeMin + ":" + finalTimeSek);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (timeSek == 0){
                        timeSek = 60;
                        timeMin--;
                    } else {
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
