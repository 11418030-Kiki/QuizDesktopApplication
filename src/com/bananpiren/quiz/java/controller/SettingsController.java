package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.xml.soap.Text;


/**
 * Created by Andreas on 2017-05-03.
 */
public class SettingsController {
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

        usernameBtn.setOnAction(e->{
            // todo: logik som hämtar user info beroende på vem som är inloggad

            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            String confirmPassword = confirmPasswordTextField.getText();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();

            UserService userService = new UserService();

            userService.updateUser(1, firstName, lastName, email, password, "Användare");



        });
    }
}