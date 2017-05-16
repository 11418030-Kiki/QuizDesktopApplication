package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.User;
import com.bananpiren.quiz.Services.UserService;
import com.bananpiren.quiz.java.view.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

public class UsersController {

    private final ObservableList<User> data = FXCollections.observableArrayList();
    private static int storedSelectedTableIndex;
    private static int storedUserId;

    private UserService userService = new UserService();

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label mailLabel;

    @FXML
    private Label userLevelLabel;

    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private TableColumn<User, String> levelColumn;

    @FXML
    private TableView<User> personTable;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    public UsersController() {
        // TODO: Make refresh method
        data.addAll(userService.findAllUsers());
    }

    @FXML
    private void initialize() {
        // Setting data to right column "cellvalue"
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<User, String>("accountLevel"));
        personTable.setItems(data);

        // Get tableselection and setting labels
        personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if(personTable.getSelectionModel().getSelectedItem() != null) {
                    editButton.setDisable(false);

                    storedSelectedTableIndex = personTable.getSelectionModel().getSelectedIndex();
                    storedUserId = data.get(storedSelectedTableIndex).getUserId();
                    System.out.println("Stored Id changed to: " + storedUserId);

                    String storedMail = data.get(storedSelectedTableIndex).getEmail();
                    String storedFirstName = data.get(storedSelectedTableIndex).getFirstName();
                    String storedLastName = data.get(storedSelectedTableIndex).getLastName();
                    String storedAccountLevel = data.get(storedSelectedTableIndex).getAccountLevel();

                    firstNameLabel.setText(storedFirstName);
                    lastNameLabel.setText(storedLastName);
                    mailLabel.setText(storedMail);
                    userLevelLabel.setText(storedAccountLevel);
                } else {
                    // Person is null, remove all the text.
                    firstNameLabel.setText("");
                    lastNameLabel.setText("");
                    mailLabel.setText("");
                    userLevelLabel.setText("");
                }
            }
        });

        addButton.setOnAction(e -> showPersonAddDialog());

        editButton.setOnAction(e -> showPersonEditDialog());

        deleteButton.setOnAction(e -> handleDeletePerson());
    }

    public void showPersonAddDialog() {
        try {
            // Load FXML file to dialog stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PersonAddDialog.fxml"));
            BorderPane page = loader.load();

            // Create the dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Lägg till ny användare");
            dialogStage.initOwner(addButton.getScene().getWindow());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonEditDialog() {
        try {
            // Load FXML file to dialog stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PersonEditDialog.fxml"));
            BorderPane page = loader.load();

            // Create the dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Redigera användare");
            dialogStage.initOwner(editButton.getScene().getWindow());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeletePerson() {

        if(personTable.getSelectionModel().getSelectedItem() != null) {
            userService.deleteUser(storedUserId);
            int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inget valt");
            alert.setHeaderText("Ingen person är vald");
            alert.setContentText("För att ta bort, välj en person");

            alert.showAndWait();
        }
    }

    public int getStoredUserId() {
        return storedUserId;
    }

    public int getStoredSelectedTableIndex() {
        return storedSelectedTableIndex;
    }

    public ObservableList<User> getData() {
        return data;
    }
}
