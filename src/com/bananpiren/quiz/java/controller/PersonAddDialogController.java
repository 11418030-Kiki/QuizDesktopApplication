package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Services.CreateUserService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.security.util.Password;

/**
 * Created by Andreas on 2017-05-09.
 */
public class PersonAddDialogController {

    private CreateUserService createUserService = new CreateUserService();

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
    private TextField passwordTextField;

    @FXML
    private void initialize() {
        userLevelChoiceBox.setItems(userLevel);
        userLevelChoiceBox.getSelectionModel().select(0);

        saveButton.setOnAction(e -> {
            String firstName = firstNameTextfield.getText();
            String lastName = lastNameTextField.getText();
            String userMail = mailTextField.getText();
            String password = passwordTextField.getText();
            //TODO: Change user password to password field instead of text field.
            String accountLevel = userLevelChoiceBox.getValue();

            System.out.println("First Name: " +firstName + "\n" +
                                "Last name: " +lastName + "\n" +
                                "Mail: " + userMail + "\n" +
                                "Password: " + password);

            createUserService.createUser(firstName, lastName, userMail, password, accountLevel);
        });

        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

}
