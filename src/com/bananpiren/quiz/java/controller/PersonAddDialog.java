package com.bananpiren.quiz.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Created by Andreas on 2017-05-09.
 */
public class PersonAddDialog {

    ObservableList<String> userLevel = FXCollections
            .observableArrayList("Användare", "Admin");

    @FXML
    private TextField mailTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ChoiceBox<String> userLevelChoiceBox;

    @FXML
    private Button saveButton;

    @FXML
    private TextField firstNameTextfield;

    @FXML
    private void initialize() {
        userLevelChoiceBox.setItems(userLevel);
    }

}
