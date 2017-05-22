package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Services.UserService;
import com.bananpiren.quiz.java.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsController {
    private UserService userService = new UserService();

//    private LoginController loginController = new LoginController();


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
//        emailTextField.setText(loginController.getCurrentUser().getEmail());


        usernameBtn.setOnAction(loginController->{
            String regexMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            StringBuilder warnings = new StringBuilder();

            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            String confirmPassword = confirmPasswordTextField.getText();

            if (email.isEmpty()) {
//                email = loginController.getCurrentUser().getEmail;
            }else if(!email.matches(regexMail)) {
                warnings.append("Mailadress har fel format!\n" + "Rätt format är xxx@xxx.xx\n");
            } else {
               emailTextField.getText();
            }
            if(password.isEmpty()){
//                password = loginController.getCurrentUser().getPassword();
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
//                userService.updateUser(main.getCurrentUser().getUserId(), main.getCurrentUser().getFirstName(), main.getCurrentUser().getLastName(), email, password, main.getCurrentUser().getAccountLevel());

//                main.getCurrentUser().setEmail(email);
//                main.getCurrentUser().setPassword(password);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succe!");
                alert.setHeaderText("Användare redigerad");
                alert.showAndWait();
            }
        });
    }

    public SettingsController() {
    }
}