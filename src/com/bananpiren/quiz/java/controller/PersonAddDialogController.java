package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Services.CreateUserService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sun.security.util.Password;

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
    private PasswordField passwordTextField;

    @FXML
    private void initialize() {
        userLevelChoiceBox.setItems(userLevel);
        userLevelChoiceBox.getSelectionModel().select(0);

        saveButton.setOnAction(e -> {
            String firstName = firstNameTextfield.getText();
            String lastName = lastNameTextField.getText();
            String userMail = mailTextField.getText();
            String password = passwordTextField.getText();

            // Validate input
            if (firstName == null || firstName.trim().isEmpty() ||
                    lastName == null || lastName.trim().isEmpty() ||
                    //TODO: Validate user mail as a mail input. Use regex!
                    userMail == null || userMail.trim().isEmpty() ||
                    password == null || password.trim().isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Alla fälten måste vara ifyllda");
                    alert.setContentText("Försök igen!");

                    alert.showAndWait();

                } else {
                    String accountLevel = userLevelChoiceBox.getValue();

                    System.out.println("First Name: " +firstName + "\n" +
                            "Last name: " +lastName + "\n" +
                            "Mail: " + userMail + "\n" +
                            "Password: " + password);

                    createUserService.createUser(firstName, lastName, userMail, password, accountLevel);
                }
        });

        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}
