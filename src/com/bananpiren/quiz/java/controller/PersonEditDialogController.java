package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.User;
import com.bananpiren.quiz.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PersonEditDialogController {

    private ObservableList<String> userLevel = FXCollections.observableArrayList("Användare", "Admin");
    private int storedUserId;

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
        UserService userService = new UserService();
        UsersController usersController = new UsersController();

        this.storedUserId = usersController.getStoredUserId();
        int storedUserTableIndex = usersController.getStoredSelectedTableIndex();
        ObservableList<User> data = usersController.getData();

        userLevelChoiceBox.setItems(userLevel);

        String storedUserAccountLevel = data.get(storedUserTableIndex).getAccountLevel();

        if (storedUserAccountLevel.equals("Användare")) {
            userLevelChoiceBox.getSelectionModel().select(0);
        } else {
            userLevelChoiceBox.getSelectionModel().select(1);
        }

        firstNameTextfield.setPromptText(data.get(storedUserTableIndex).getFirstName());
        lastNameTextField.setPromptText(data.get(storedUserTableIndex).getLastName());
        mailTextField.setPromptText(data.get(storedUserTableIndex).getEmail());
        passwordTextField.setPromptText(data.get(storedUserTableIndex).getPassword());

        firstNameTextfield.setText(data.get(storedUserTableIndex).getFirstName());
        lastNameTextField.setText(data.get(storedUserTableIndex).getLastName());
        mailTextField.setText(data.get(storedUserTableIndex).getEmail());
        passwordTextField.setText(data.get(storedUserTableIndex).getPassword());

        saveButton.setOnAction(e -> {
            String regexMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            String firstName = firstNameTextfield.getText();
            String lastName = lastNameTextField.getText();
            String userMail = mailTextField.getText();
            String password = passwordTextField.getText();

            StringBuilder warnings = new StringBuilder();

            // Validate input
            if (firstName == null || firstName.trim().isEmpty()) {
                warnings.append("Förnamn är inte ifyllt!\n");
            } else {
                firstNameTextfield.getText();
            }
            if (lastName == null || lastName.trim().isEmpty()) {
                warnings.append("Efternamn är inte ifyllt!\n");
            } else {
                lastNameTextField.getText();
            }
            if (userMail == null || userMail.trim().isEmpty()) {
                warnings.append("Mailadress är inte ifyllt!\n");
            }
            if(!userMail.matches(regexMail)) {
                warnings.append("Mailadress har fel format!\n" + "Rätt format är xxx@xxx.xx\n");
            } else {
                mailTextField.getText();
            }
            if(password == null || password.trim().isEmpty()) {
                warnings.append("Lösenord är inte ifyllt!\n");
            }else {
                passwordTextField.getText();
            }

            if(warnings.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("" + warnings);
                alert.setContentText("Försök igen!");

                alert.showAndWait();
            } else {
                String accountLevel = userLevelChoiceBox.getValue();

                userService.updateUser(storedUserId, firstName, lastName, userMail, password, accountLevel);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succe!");
                alert.setHeaderText("Användare redigerad");
                alert.showAndWait();

                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
            }
        });

        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}