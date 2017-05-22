package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.java.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField userPasswordTextField;

    @FXML
    private Button loginButton;

    private MainController mainController = new MainController();
    private Main main = new Main();
    private Stage primaryStage = new Stage();

    public LoginController(){}

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> {
            try {
                main.showMainView(primaryStage);
                mainController.showHome();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
