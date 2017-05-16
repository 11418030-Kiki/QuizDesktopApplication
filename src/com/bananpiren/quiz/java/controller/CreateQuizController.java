package com.bananpiren.quiz.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    private ArrayList<String> questionList = new ArrayList<>();

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
    private Button buttonAddQuestion;

    private TextField newQuestion;

    @FXML
    private void initialize() {

        // Timelimit
        timeLimitCheckBox.setOnAction(e -> {
            if (timeLimitCheckBox.isSelected()) {
                timeLimitGridPane.setVisible(true);
            } else {
                timeLimitGridPane.setVisible(false);
            }
        });

        buttonAddQuestion.setOnAction(e -> {
            addQuestion();
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
            System.out.println("newQuestion.getText() = " + newQuestion.getText());

            createQuizServices.createQuiz(quizName, timeLimit, quizStartDate, quizEndDate);
        }

    }

    private void theQuestion() {
        // add question to list
        if(questionNumber > 1) {
            createQuizServices.addQuizQuestionObject(newQuestion.getText());
        }
    }

    private void addQuestion() {
        theQuestion();

        // TODO: add questions and answers
        newQuestion = new TextField();

        newQuestion.setPromptText("Fråga " + questionNumber++);

        TextField newAnswer = new TextField();
        newAnswer.setPromptText("Svar " + answerNumber++);
        TextField newAnswer2 = new TextField();
        newAnswer2.setPromptText("Svar " + answerNumber++);
        TextField newAnswer3 = new TextField();
        newAnswer3.setPromptText("Svar " + answerNumber++);
        TextField newAnswer4 = new TextField();
        newAnswer4.setPromptText("Svar " + answerNumber++);
        vboxAddQuestions.getChildren().addAll(newQuestion, newAnswer, newAnswer2, newAnswer3, newAnswer4);

        questionList.add(newQuestion.getPromptText());

    }
}
