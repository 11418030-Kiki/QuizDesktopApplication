package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.User;
import com.bananpiren.quiz.Services.UserService;
import com.bananpiren.quiz.java.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
    private void initialize(){
        emailTextField.setText(currentUser.getEmail());


        usernameBtn.setOnAction(e->{
            String regexMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            StringBuilder warnings = new StringBuilder();

            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            String confirmPassword = confirmPasswordTextField.getText();

            if (email.isEmpty()) {
                email = currentUser.getEmail();
            }else if(!email.matches(regexMail)) {
                warnings.append("Mailadress har fel format!\n" + "Rätt format är xxx@xxx.xx\n");
            } else {
               emailTextField.getText();
            }
            if(password.isEmpty()){
                password = currentUser.getPassword();
            }else if(!password.matches(confirmPassword)){
                warnings.append("Lösenord matchar inte!");
            }else{
                passwordTextField.getText();
                confirmPasswordTextField.getText();
            }

            if(warnings.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("" + warnings);
                alert.setContentText("Försök igen!");

                alert.showAndWait();
            } else {
                userService.updateUser(Main.currentUserId, currentUser.getFirstName(), currentUser.getLastName(), email, password, currentUser.getAccountLevel());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succe!");
                alert.setHeaderText("Användare redigerad");
                alert.showAndWait();
            }
        });
    }

    public SettingsController() {
        currentUser = userService.findUserById(Main.currentUserId);
    }
}