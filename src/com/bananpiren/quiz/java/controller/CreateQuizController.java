package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.QuestionAnswers;
import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.QuizQuestions;
import com.bananpiren.quiz.Services.AnswerService;
import com.bananpiren.quiz.Services.QuestionService;
import com.bananpiren.quiz.Services.QuizService;
import com.bananpiren.quiz.java.view.Main;
import com.bananpiren.quiz.java.model.NewQuestionType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class CreateQuizController {

    private int timeLimit = 0;

    private ListView<Pane> QuestionList;

    private ArrayList<NewQuestionType> newQuestionType = new ArrayList<>();

    @FXML
    private CheckBox timeLimitCheckBox;

    @FXML
    private GridPane timeLimitGridPane;

    @FXML
    private CheckBox selfCorrectingCheckBox;

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
    private Button buttonAddOpenAnswerQuestion;

    @FXML
    private Button buttonRemoveCurrentQuestion;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void initialize() {

        QuestionList = new ListView<>();
        vboxAddQuestions.setPrefHeight(265);
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
        vboxAddQuestions.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue((newValue).doubleValue()));

        buttonAddMultipleAnswerQuestion.setOnAction(e -> addMultipleAnswerQuestion());

        buttonAddSingleAnswerQuestion.setOnAction(e -> addSingleAnswerQuestion());

        buttonAddOpenAnswerQuestion.setOnAction(e -> {
            selfCorrectingCheckBox.setSelected(false);
            selfCorrectingCheckBox.setDisable(true);
            buttonAddOpenAnswerQuestion();
        });

        buttonRemoveCurrentQuestion.setOnAction(e -> removeSelectedQuestion());

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
        LocalDate quizStartDate = datePickerStartDate.getValue();
        LocalDate quizEndDate = datePickerEndDate.getValue();
        String selfcorrectingvalue;

        // Check if Quiz name is entered
        if (quizName.isEmpty()) {
            warnings.append("Quiznamn saknas!\n");
        } else {
            quizName = textFieldQuizName.getText();
        }

        // Check if Starting date is entered
        if (datePickerStartDate.getValue() == null) {
            warnings.append("Startdatum saknas!\n");
        }

        // Check if Ending date is entered
        if (datePickerEndDate.getValue() == null) {
            warnings.append("Slutdatum saknas!\n");
        }

        // Check if Quiz is selfcorrecting or not
        if (timeLimitCheckBox.isSelected()) {
            selfcorrectingvalue = "yes";
        } else {
            selfcorrectingvalue = "no";
        }

        if (warnings.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Alla fält är inte ifyllda!\n" + warnings.toString());
            alert.showAndWait();
        } else {
            //TODO: Koolla validera av svar?
            // Saves Quiz to database
            Quiz quiz = new Quiz(quizName, timeLimit, quizStartDate.toString(), quizEndDate.toString(), selfcorrectingvalue);
            QuizService.create(quiz);

            // ArrayLists for Question and Answer entities
            ArrayList<QuizQuestions> quizQuestions = new ArrayList<>();
            ArrayList<QuestionAnswers> answers = new ArrayList<>();

            // Check for questions and add to questions list
            for (NewQuestionType element : newQuestionType) {
                String questionType = "";
                switch (element.getQuestionType()) {
                    case "multipleAnswer":
                        questionType = "multiple";
                        break;
                    case "singleAnswer":
                        questionType = "single";
                        break;
                    case "openAnswer":
                        questionType = "open";
                        break;
                }

                QuizQuestions questions = new QuizQuestions(element.questionTextField.getText(), questionType, quiz);
                quizQuestions.add(questions);

                // Check for answers and add to answers list
                for (int j = 0; j < element.newAnswerTextField.length ; j++) {
                    int correctAnswer = 0;
                    switch (questionType) {
                        case "multiple":
                            if (element.answerCheckbox[j].isSelected()) {
                                correctAnswer = 1;
                            } else {
                                correctAnswer = 0;
                            }
                            break;
                        case "single":
                            if (element.radioButtonAnswer[j].isSelected()) {
                                correctAnswer = 1;
                            } else {
                                correctAnswer = 0;
                            }
                            break;
                        case "open":
                            correctAnswer = 0;
                            break;
                    }

                    QuestionAnswers answer = new QuestionAnswers(element.newAnswerTextField[j].getText(), correctAnswer, questions);
                    answers.add(answer);
                }
            }

            // Saves all Question and Answer objects to database
            QuestionService.create(quizQuestions);
            AnswerService.create(answers);

            NewQuestionType.resetQuestionsAndAnswerNumbers();

            // Feedback after successfully added quiz
            successfullyCreatedQuiz();
        }
    }

    private void successfullyCreatedQuiz() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succe!");
        alert.setHeaderText("Quizet sparades");
        alert.setContentText("Vill du skapa fler quiz?");

        ButtonType noButton = new ButtonType("Nej", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType yesButton = new ButtonType("Ja");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        // If user don't want to create more quiz go to edit quiz page
        if (result.get() == noButton) {
            try {
                Main.showEditQuiz();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // If user want's to create more quize refresh page
            try {
                Main.showCreateQuiz();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private void buttonAddOpenAnswerQuestion() {
        NewQuestionType newOpenQuestion = new NewQuestionType(QuestionList);
        newOpenQuestion.openAnswer();
        newQuestionType.add(newOpenQuestion);
    }

    private void removeSelectedQuestion() {
        int selectedQuestion = QuestionList.getSelectionModel().getSelectedIndex();

        QuestionList.getItems().remove(selectedQuestion);
        newQuestionType.remove(selectedQuestion);
    }
}