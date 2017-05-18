package com.bananpiren.quiz.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.bananpiren.quiz.Services.CreateQuizService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateQuizController {

    private CreateQuizService createQuizServices = new CreateQuizService();

    private int questionNumber = 1;
    private int answerNumber = 1;
    private int timeLimit = 0;

    private TextField newQuestion;

    public TextField[] newAnswer;
    public CheckBox[] answerCheckbox;
    public RadioButton[] radioButtonAnswer;
    public HBox[] hBox;

    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<String> qList = new ArrayList<>();

    private LocalDate quizEndDate;
    private LocalDate quizStartDate;

    @FXML
    private CheckBox timeLimitCheckBox;

    @FXML
    private GridPane timeLimitGridPane;

    @FXML
    private Slider sliderTime;

    @FXML
    private DatePicker datePickerEndDate;

    @FXML
    private Button buttonCreateQuiz;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private VBox vboxAddQuestions;

    @FXML
    private TextField textFieldQuizName;

    @FXML
    private Label labelMinutes;

    @FXML
    private Button buttonAddSingleAnswerQuestion;

    @FXML
    private Button buttonAddMultipleAnswerQuestion;

    @FXML
    private void initialize() {

        // Timelimit
        timeLimitCheckBox.setOnAction(e -> {
            if (timeLimitCheckBox.isSelected()) {
                timeLimitGridPane.setMaxHeight(60);
                timeLimitGridPane.setPrefHeight(60);
                timeLimitGridPane.setMinHeight(60);
                timeLimitGridPane.setVisible(true);
            } else {
                timeLimitGridPane.setVisible(false);
                timeLimitGridPane.setMaxHeight(0);
                timeLimitGridPane.setPrefHeight(0);
                timeLimitGridPane.setMinHeight(0);
            }
        });

        buttonAddMultipleAnswerQuestion.setOnAction(e -> {
            addMultipleAnswerQuestion();
        });

        buttonAddSingleAnswerQuestion.setOnAction(e -> {
            addSingleAnswerQuestion();
        });

        buttonCreateQuiz.setOnAction(e -> {
            try {
                createQuiz();
            } catch (ParseException e1) {
                System.out.println("Coulnd't create quiz!");
            }
        });

        // Show slidervalue to label
        sliderTime.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelMinutes.setText(newValue.intValue() + " minuter");

            timeLimit = newValue.intValue();
        });

    }

    private void createQuiz() throws ParseException {
        //TODO: add logic for taking in information and adding quiz to database
        StringBuilder warnings = new StringBuilder();

        String quizName = textFieldQuizName.getText();

        // Check if Quiz name is entered
        if (quizName.isEmpty()) {
            warnings.append("Quiznamn saknas!\n");
        } else {
            quizName = textFieldQuizName.getText();
            System.out.println("Quiznamn: " + quizName);
        }

        // Check if Starting date is entered
        if (datePickerStartDate == null) {
            warnings.append("Startdatum saknas!\n");
        } else {
            System.out.println("startdatum är " + datePickerStartDate.getValue());
            quizStartDate = datePickerStartDate.getValue();
        }

        // Check if Ending date is entered
        if (datePickerEndDate == null) {
            warnings.append("Slutdatum saknas!\n");
        } else {
            System.out.println("slutdatum är " + datePickerEndDate.getValue());
            quizEndDate = datePickerEndDate.getValue();
        }

        if (warnings.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Alla fält är inte ifyllda!\n" + warnings.toString());
            alert.showAndWait();
        } else {
            // add last question
            theQuestion();
            theAnswers();

            createQuizServices.createQuiz(quizName, timeLimit, quizStartDate, quizEndDate, qList);

        }
    }

    private void theQuestion() {
        // add question to list
        if (questionNumber > 1) {
            // adding the input to the list
            qList.add(newQuestion.getText());


        }
    }

    private void theAnswers() {
        if (questionNumber > 1) {
            for (int i = 0; i < newAnswer.length; i++) {
                createQuizServices.addQuizAnswerObject(newAnswer[i].getText(), answerCheckbox[i].isSelected());
            }
        }
    }

    private void addMultipleAnswerQuestion() {
        theQuestion();
        theAnswers();

        Label questionLabel = new Label();
        questionLabel.setText("Fråga " + questionNumber);
        newQuestion = new TextField();
        newQuestion.setText("Fråga " + questionNumber);
        newQuestion.setMaxWidth(300);
        vboxAddQuestions.getChildren().addAll(questionLabel, newQuestion);

        newAnswer = new TextField[4];
        answerCheckbox = new CheckBox[4];
        hBox = new HBox[4];

        for (int i = 0; i < 4; i++) {
            hBox[i] = new HBox();
            Button deleteButton = new Button("X");
            newAnswer[i] = new TextField();
            newAnswer[i].setText("Fråga " + questionNumber + " svar " + answerNumber++);
            hBox[i].getChildren().addAll(deleteButton, newAnswer[i]);

            answerCheckbox[i] = new CheckBox("Rätt svar");
            hBox[i].getChildren().add(answerCheckbox[i]);

//            deleteButton.setOnAction(e -> {
//                vboxAddQuestions.getChildren().removeAll(this.vboxAddQuestions);
//            });

            vboxAddQuestions.getChildren().add(hBox[i]);
        }

        questionNumber++;
        answerNumber = 1;
    }

    private void addSingleAnswerQuestion() {
//        theQuestion();
//        theAnswers();

        Label questionLabel = new Label();
        questionLabel.setText("Fråga " + questionNumber);
        newQuestion = new TextField();
        newQuestion.setText("Fråga " + questionNumber);
        newQuestion.setMaxWidth(300);
        vboxAddQuestions.getChildren().addAll(questionLabel, newQuestion);

        newAnswer = new TextField[4];
        ToggleGroup answerToggleGroup = new ToggleGroup();
        radioButtonAnswer = new RadioButton[4];
        hBox = new HBox[4];

        for (int i = 0; i < 4; i++) {
            hBox[i] = new HBox();
            Button deleteButton = new Button("X");
            newAnswer[i] = new TextField();
            newAnswer[i].setText("Fråga " + questionNumber + " svar " + answerNumber++);
            hBox[i].getChildren().addAll(deleteButton, newAnswer[i]);

            radioButtonAnswer[i] = new RadioButton("Rätt svar");
            radioButtonAnswer[i].setToggleGroup(answerToggleGroup);
            hBox[i].getChildren().add(radioButtonAnswer[i]);

//            deleteButton.setOnAction(e -> {
//                vboxAddQuestions.getChildren().removeAll(this.vboxAddQuestions);
//            });

            vboxAddQuestions.getChildren().add(hBox[i]);
        }
        questionList.add(newQuestion.getPromptText());
        questionNumber++;
        answerNumber = 1;
    }
}