package com.bananpiren.quiz.java.model;

import javafx.scene.control.Alert;

public class Alerts {

    public void informationAlert(String alertTitle, String alertHeader, String alertContent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(alertHeader);
        alert.setContentText(alertContent);

        alert.showAndWait();
    }

    public void errorAlert(StringBuilder warnings) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("" + warnings);
        alert.setContentText("Försök igen!");

        alert.showAndWait();
    }
}
