package com.bananpiren.quiz.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;


/**
 * Created by Andreas on 2017-05-03.
 */
public class SettingsController {
    @FXML
    private TextField emailTextField;

    @FXML
    private Button emailBtn;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button passwordBtn;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button usernameBtn;

    @FXML
    private void initialize(){

        emailBtn.setOnAction(e->{
            String email = emailTextField.getText();

            if (email.contains("@") && email.endsWith(".com") || email.endsWith(".se")){
                JOptionPane.showMessageDialog(null, "" +
                        "Din email-adress har sparats.\n Din nya email är: "+email);

                //Todo logic for updating users email in database
            }else{
                JOptionPane.showMessageDialog(null, "Skriv in en korrekt email-adress för att gå vidare");
            }
        });

        passwordBtn.setOnAction(e->{
            String password = passwordTextField.getText();
            String confirmPassword = confirmPasswordTextField.getText();

            if (password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(null, "Ditt lösenord har sparats!");

                //todo Logic for updating userpassword in database
            }else{
                JOptionPane.showMessageDialog(null, "Ditt lösenord stämmer inte överens med ditt bekräftade lösenord.");
            }
        });

        usernameBtn.setOnAction(e->{

        });
    }
}