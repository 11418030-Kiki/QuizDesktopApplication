package com.bananpiren.quiz.java.model;
import javafx.application.Platform;
import javafx.scene.control.*;

public class QuizTimer {
    private static Thread updateQuizTimeThread;

    public static void quizTimerClock(int quizTime, Label quizTimeLabel, Button sendQuizButton) {
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
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (timeSek == 0){
                        timeSek = 59;
                        timeMin--;
                    } else {
                        timeSek--;
                    }
                }

            Platform.runLater(sendQuizButton::fire);
        };

        updateQuizTimeThread = new Thread(updateQuizTimeRunnable);
        updateQuizTimeThread.start();
    }

    public void killTimer() {
        System.out.println("Döda klockan körs.");
        updateQuizTimeThread.stop();
    }
}
