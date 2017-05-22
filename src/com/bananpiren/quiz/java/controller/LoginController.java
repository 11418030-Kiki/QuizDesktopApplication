package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.User;
import com.bananpiren.quiz.Services.UserService;
import com.bananpiren.quiz.java.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField userEmailTextField;

    @FXML
    private PasswordField userPasswordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Label informationOutputTextField;

    private MainController mainController = new MainController();
    private Stage primaryStage = new Stage();
    private UserService userService = new UserService();
    private Main main = new Main();

    private static User currentUser;

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> checkPassword());
    }

    private void checkPassword() {
        String storedUserEmail = userEmailTextField.getText();
        String storeUserPassword = userPasswordTextField.getText();

        currentUser = userService.findUserByEmail(storedUserEmail);

        if (storeUserPassword.equals(currentUser.getPassword())) try {
            main.showMainView(primaryStage);
            mainController.showHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
        else {
            informationOutputTextField.setText("Email and/or password incorrect.");
            System.out.println("Incorrect");
        }
    }

    private User getCurrentUser() {
        return currentUser;
    }
}
