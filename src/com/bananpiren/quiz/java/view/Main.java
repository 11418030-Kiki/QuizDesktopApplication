package com.bananpiren.quiz.java.view;

import com.bananpiren.quiz.Entity.User;
import com.bananpiren.quiz.Services.UserService;
import com.bananpiren.quiz.java.controller.LoginController;
import com.bananpiren.quiz.java.controller.MainController;
import com.bananpiren.quiz.java.controller.UsersController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Main extends Application {
    private static Stage loginStage;
    public static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginStage = primaryStage;
        loginStage.setTitle("Bananpiren Dekstop Quiz");

        showLoginScreen();
    }

    // Loading login screen
    public void showLoginScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Login.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        loginStage.setScene(scene);
        loginStage.show();
    }

    // Loading start page
    public void showMainView(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Bananpiren Dekstop Quiz");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Main.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        loginStage.hide();
    }

    // Start page - welcoming user
    public static void showStart() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Start.fxml"));
        BorderPane mainMenu = loader.load();
        mainLayout.setCenter(mainMenu);
    }

    // Create Quiz
    public static void showCreateQuiz() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("CreateQuiz.fxml"));
        ScrollPane mainMenu = loader.load();
        mainLayout.setCenter(mainMenu);
    }

    // Edit Quiz
    public static void showEditQuiz() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("EditQuiz.fxml"));
        ScrollPane mainMenu = loader.load();
        mainLayout.setCenter(mainMenu);
    }

    // Results
    public static void showResults() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Results.fxml"));
        BorderPane mainMenu = loader.load();
        mainLayout.setCenter(mainMenu);
    }

    // Users
    public static void showUsers() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Users.fxml"));
        BorderPane mainMenu = loader.load();
        mainLayout.setCenter(mainMenu);
    }

    // Statistics
    public static void showStatistics() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Statistics.fxml"));
        BorderPane mainMenu = loader.load();
        mainLayout.setCenter(mainMenu);
    }

    // Settings
    public static void showSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Settings.fxml"));
        BorderPane mainMenu = loader.load();
        mainLayout.setCenter(mainMenu);
    }

    public static void main(String[] args) {
        launch(args);
    }
}