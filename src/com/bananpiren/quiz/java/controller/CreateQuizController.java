package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.QuestionAnswers;
import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.QuizQuestions;
import com.bananpiren.quiz.Services.AnswerService;
import com.bananpiren.quiz.Services.QuestionService;
import com.bananpiren.quiz.Services.QuizService;
import com.bananpiren.quiz.java.model.NewQuestionType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateQuizController {

    private int timeLimit = 0;

    ListView<Pane> QuestionList;

    ArrayList<NewQuestionType> newQuestionType = new ArrayList<NewQuestionType>();

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
    private ScrollPane scrollPane;

    @FXML
    private void initialize() {

        QuestionList = new ListView<Pane>();
        vboxAddQuestions.getChildren().addAll(QuestionList);

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
                sliderTime.setValue(0);
            }
        });

        // Autoscroll when adding questions
        vboxAddQuestions.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(((Double) newValue).doubleValue());
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
                System.out.println("Couldn't create quiz!");
            }
        });

        // Show slidervalue to label
        sliderTime.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelMinutes.setText(newValue.intValue() + " minuter");
            timeLimit = newValue.intValue();
        });
    }

    private void createQuiz() throws ParseException {
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
            // Saves Quiz to database
            Quiz quiz = new Quiz(quizName, timeLimit, quizStartDate.toString(), quizEndDate.toString());
            QuizService.create(quiz);

            // ArrayLists for Question and Answer entities
            ArrayList<QuizQuestions> quizQuestions = new ArrayList<>();
            ArrayList<QuestionAnswers> answers = new ArrayList<>();

            // Check for questions and add to questions list
            int i = 0;
            for (NewQuestionType element : newQuestionType) {
                System.out.println(i);

                QuizQuestions quest = new QuizQuestions(element.questionTextField.getText(), quiz);
                quizQuestions.add(quest);
                i++;

                // Check for answers and add to answers list
                for(int j = 0; j < 4; j++) {
                    int correctAnswer = 0;
                    //TODO: add if radiobutton is checked
                    if (element.answerCheckbox[j].isSelected()) {
                        correctAnswer = 1;
                    } else {
                        correctAnswer = 0;
                    }
                    QuestionAnswers answer = new QuestionAnswers(element.newAnswerTextField[j].getText(), correctAnswer, quest);
                    answers.add(answer);
                }
            }

            // Saves all Question and Answer objects to database
            QuestionService.create(quizQuestions);
            AnswerService.create(answers);
        }
    }

    private void addMultipleAnswerQuestion() {
        NewQuestionType newMultiQuestion = new NewQuestionType(QuestionList);
        newMultiQuestion.multipleAnswer();
        newQuestionType.add(newMultiQuestion);
    }

    private void addSingleAnswerQuestion() {
        NewQuestionType newSingleQuestion = new NewQuestionType(QuestionList);
        newSingleQuestion.singleAnswer();
        newQuestionType.add(newSingleQuestion);
    }
}