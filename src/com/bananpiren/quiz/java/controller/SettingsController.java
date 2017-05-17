package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.User;
import com.bananpiren.quiz.Services.UserService;
import com.bananpiren.quiz.java.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class SettingsController {

    private UserService userService = new UserService();
    private User currentUser = new User();

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private Button usernameBtn;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private void initialize(){
        firstNameTextField.setText(currentUser.getFirstName());
        lastNameTextField.setText(currentUser.getLastName());
        emailTextField.setText(currentUser.getEmail());
        passwordTextField.setText(currentUser.getPassword());

        usernameBtn.setOnAction(e->{
            // todo: logik som hämtar user info beroende på vem som är inloggad

            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            String confirmPassword = confirmPasswordTextField.getText();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();

            userService.updateUser(Main.currentUserId, firstName, lastName, email, password, "Användare");
        });
    }

    public SettingsController() {
        //TODO: Lägg till nuvarandes användar id istället för 10!
        currentUser = userService.findUserById(Main.currentUserId);
    }
}