package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.QuestionAnswers;
import com.bananpiren.quiz.Entity.QuizQuestions;
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

    private TextField newQuestion;

    public TextField[] newAnswer;
    public CheckBox[] answerCheckbox;
    public RadioButton[] radioButtonAnswer;

    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<QuizQuestions> qList = new ArrayList<>();
    private ArrayList<QuestionAnswers> aList = new ArrayList<>();

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

            createQuizServices.createQuiz(quizName, timeLimit, quizStartDate, quizEndDate, qList, aList);

        }
    }

    private void theQuestion() {
        // add question to list
        if (questionNumber > 1) {
            // adding the input to the list
            QuizQuestions q = new QuizQuestions();
            q.setQuestion(newQuestion.getText());
            qList.add(q);

        }
    }

    // cascadetype, primary key eventuellt som kopplas till
    // foreignkey
    private void theAnswers() {
        if (questionNumber > 1) {
            for (int i = 0; i < newAnswer.length; i++) {
                QuestionAnswers qa = new QuestionAnswers();
                qa.setAnswer(newAnswer[i].getText());
                qa.setCorrectAnswer(answerCheckbox[i].isSelected());
                aList.add(qa);
////              createQuizServices.addQuizAnswerObject(newAnswer[i].getText(), answerCheckbox[i].isSelected());
//                aList.add(newAnswer[i].getText(), answerCheckbox[i].isSelected());
            }
        }
    }

    private void addMultipleAnswerQuestion() {
        theQuestion();
        theAnswers();

        newQuestion = new TextField();
        newQuestion.setPromptText("Fråga " + questionNumber);
        vboxAddQuestions.getChildren().add(newQuestion);

        newAnswer = new TextField[4];
        answerCheckbox = new CheckBox[4];

        for (int i = 0; i < 4; i++) {
            newAnswer[i] = new TextField();
            newAnswer[i].setPromptText("Fråga " + questionNumber + " svar " + answerNumber++);
            vboxAddQuestions.getChildren().add(newAnswer[i]);

            answerCheckbox[i] = new CheckBox("Rätt svar");
            vboxAddQuestions.getChildren().add(answerCheckbox[i]);
        }

        questionList.add(newQuestion.getPromptText());
        questionNumber++;
    }

    private void addSingleAnswerQuestion() {
//        theQuestion();
//        theAnswers();

        newQuestion = new TextField();
        newQuestion.setPromptText("Fråga " + questionNumber);
        vboxAddQuestions.getChildren().add(newQuestion);

        newAnswer = new TextField[4];
        ToggleGroup answerToggleGroup = new ToggleGroup();
        radioButtonAnswer = new RadioButton[4];

        for (int i = 0; i < 4; i++) {
            newAnswer[i] = new TextField();
            newAnswer[i].setPromptText("Fråga " + questionNumber + " svar " + answerNumber++);
            vboxAddQuestions.getChildren().add(newAnswer[i]);

            radioButtonAnswer[i] = new RadioButton("Rätt svar");
            radioButtonAnswer[i].setToggleGroup(answerToggleGroup);
            vboxAddQuestions.getChildren().add(radioButtonAnswer[i]);
        }
        questionList.add(newQuestion.getPromptText());
        questionNumber++;
    }
}