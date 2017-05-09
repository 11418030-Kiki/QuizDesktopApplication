package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.java.view.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Andreas on 2017-05-03.
 */
public class UsersController {

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label mailLabel;

    @FXML
    private Label userLevelLabel;

    @FXML
    private TableColumn<?, ?> levelColumn;

    @FXML
    private TableColumn<?, ?> firstNameColumn;

    @FXML
    private Button editButton;

    @FXML
    private TableView<?> personTable;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<?, ?> lastNameColumn;

    @FXML
    private void initialize() {

        addButton.setOnAction(e -> {
            try {
                Stage stage;
                Parent root;

                stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("AddPersonDialog.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("My modal window");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        editButton.setOnAction(e -> {
            showPersonEditDialog();
        });

        deleteButton.setOnAction(e -> {
            handleDeletePerson();
        });

    }


    // Button Lägg till
    public void showPersonAddDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("com/bananpiren/quiz/java/view/PersonAddDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Lägg till ny användare");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
//            PersonEditDialogController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Button Redigera
    public void showPersonEditDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Redigera användare");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
//            PersonEditDialogController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Button Ta bort
    @FXML
    private void handleDeletePerson() {

        if(personTable.getSelectionModel().getSelectedItem() != null) {
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

    //  Show Person details (Person person) - in method head)
//    private void showPersonDetails(Person person) {
//        if (person != null) {
//            // TODO: Get data from JPA person
//
//            // Fill the labels with info from the person object.
//            firstNameLabel.setText(person.getFirstName());
//            lastNameLabel.setText(person.getLastName());
//            mailLabel.setText(person.getStreet());
//            userLevelLabel.setText(Integer.toString(person.getPostalCode()));
//
//            // birthdayLabel.setText(...);
//        } else {
//            // Person is null, remove all the text.
//            firstNameLabel.setText("");
//            lastNameLabel.setText("");
//            mailLabel.setText("");
//            userLevelLabel.setText("");
//        }
//    }
}
