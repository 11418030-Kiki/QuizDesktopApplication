package com.bananpiren.quiz.java.controller;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.TakeQuiz;
import com.bananpiren.quiz.Services.QuizService;
import com.bananpiren.quiz.java.view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StartController {

    private ObservableList<Quiz> data = FXCollections.observableArrayList();
    //    private ObservableList<VBox> vbox = FXCollections.observableArrayList();
    private static ArrayList<TakeQuiz> takeQuizList = new ArrayList<>();

    private VBox vbox;

    private QuizService quizService = new QuizService();

    public static int currentQuizId;

    @FXML
    private TableColumn<Quiz, Integer> quizIdColumn;

    @FXML
    private TableColumn<Quiz, String> quizNameColumn;

    @FXML
    private TableView<Quiz> quizTableView;

    @FXML
    private TableColumn<Quiz, String> quizEndDateColumn;

    @FXML
    private TableColumn<Quiz, String> quizStartDateColumn;

    @FXML
    private Button takeQuizButton;

    private static ArrayList<CheckBox> multiAnswerList = new ArrayList<>();
    private static ArrayList<RadioButton> singleAnswerList = new ArrayList<>();


    public StartController() {
        data.addAll(quizService.findAllQuiz());
    }

    @FXML
    private void initialize() {
        // Setting data to right column "cellvalue"
        quizIdColumn.setCellValueFactory(new PropertyValueFactory<Quiz, Integer>("quizId"));
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizName"));
        quizEndDateColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizEndDate"));
        quizStartDateColumn.setCellValueFactory(new PropertyValueFactory<Quiz, String>("quizStartDate"));
        quizTableView.setItems(data);

        takeQuizButton.setDisable(true);
        quizTableView.getSelectionModel().selectedIndexProperty().addListener(y -> {
            takeQuizButton.setDisable(false);
        });


        takeQuizButton.setOnAction((ActionEvent e) -> {
            String quizEndDate = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizEndDate();
            String quizStartDate = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizStartDate();
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateQuizEndDate = inputFormat.parse(quizEndDate);
                Date dateQuizStartDate = inputFormat.parse(quizStartDate);
                Calendar calQuizEndDate = Calendar.getInstance();
                Calendar calquizStartDate = Calendar.getInstance();
                calQuizEndDate.setTime(dateQuizEndDate);
                calquizStartDate.setTime(dateQuizStartDate);
                Calendar calToday = Calendar.getInstance();
                calToday.set(Calendar.HOUR_OF_DAY, 0);
                calToday.set(Calendar.MINUTE, 0);
                calToday.set(Calendar.SECOND, 0);
                calToday.set(Calendar.MILLISECOND, 0);
                if (calQuizEndDate.compareTo(calToday) <= 0) {
                    Alert toLate = new Alert(Alert.AlertType.ERROR);
                    toLate.setTitle("Meddelande");
                    toLate.setHeaderText("Datumet passerat");
                    toLate.setContentText("Datumet för provet har redan passerat");
                    toLate.showAndWait();
                }else if(calquizStartDate.compareTo(calToday) >0){
                    Alert toEarly = new Alert(Alert.AlertType.INFORMATION);
                    toEarly.setTitle("Meddelande");
                    toEarly.setHeaderText("Quizet har inte startat");
                    toEarly.setContentText("Quizets startdatum har inte inträffat");
                    toEarly.showAndWait();
                } else {

                    // get the Id of the current Quiz
                    currentQuizId = quizTableView.getSelectionModel().selectedItemProperty().getValue().getQuizId();

                    VBox newCoolVbox = new VBox();

                    // get the list of the current quiz from the database
                    // building the querys
                    takeQuizList = quizService.currentQuiz(currentQuizId);

                    // displaying the question and answers on vbox
                    vbox = createQuizQuestions();
                    newCoolVbox.getChildren().addAll(vbox);

                // create the TakeQuizController
                    TakeQuizController takeQuizController = new TakeQuizController();

                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Main.class.getResource("TakeQuiz.fxml"));
                        BorderPane takeQuiz = loader.load();
                        takeQuiz.setCenter(newCoolVbox);
                        Main.mainLayout.setCenter(takeQuiz);
                    } catch (IOException f) {
                        System.out.println("Couldn't load TakeQuiz.fxml: " + f);
                    }



                }
            } catch (Exception f) {
                System.out.println(f);
            }

        });
    }


    // TODO: för openquestionfrågor: För varje openquestion, minska len med 3
    VBox createQuizQuestions() {

        // length of the list divided with the number of questions
        int len = takeQuizList.size() / 4;

        String[] questionName = new String[len];
        Label[] questionLabel = new Label[len];
        String questionType = "";

        String[] answer = new String[takeQuizList.size()];
        Label[] answerLabel = new Label[takeQuizList.size()];
        CheckBox[] answerCheckbox = new CheckBox[takeQuizList.size()];

        RadioButton[] answerButton = new RadioButton[takeQuizList.size()];

        ToggleGroup[] toggleGroups = new ToggleGroup[len]; // set with the number of questions

        HBox[] answerBox = new HBox[takeQuizList.size()];

        VBox questionBox = new VBox();

        int incQuest = 0;
        int incAnswer = 0;
        int answerNo = 4;

        // loop through the questions
        for (int i = 0; i < len; i++) {
            // increment the question with the number of answers and get the question
            incQuest = i * answerNo;

            questionName[i] = takeQuizList.get(incQuest).getQuestion();
            questionType = takeQuizList.get(incQuest).getQuestionType();

            questionLabel[i] = new Label(questionName[i]);
            questionBox.getChildren().add(questionLabel[i]);
            questionBox.setSpacing(5);
            toggleGroups[i] = new ToggleGroup();
            Separator separator = new Separator();
            separator.setValignment(VPos.CENTER);

            // loop through the answers
            for (int j = 0; j < answerNo; j++) {
                incAnswer = incQuest + j;

                answer[j] = takeQuizList.get(incAnswer).getAnswer();

                answerLabel[j] = new Label(answer[j]);
                answerBox[j] = new HBox();


                answerBox[j].setSpacing(5);

                // checks what kind of question
                if (questionType.equals("multiple")) {
                    answerCheckbox[j] = new CheckBox();
                    answerBox[j].getChildren().add(answerCheckbox[j]);
                    answerBox[j].getChildren().add(answerLabel[j]);

                    multiAnswerList.add(answerCheckbox[j]);

                } else {
                    answerButton[j] = new RadioButton();
                    answerButton[j].setToggleGroup(toggleGroups[i]);

                    answerBox[j].getChildren().add(answerButton[j]);
                    answerBox[j].getChildren().add(answerLabel[j]);

                    singleAnswerList.add(answerButton[j]);
                }

                questionBox.getChildren().add(answerBox[j]);
            }
            questionBox.getChildren().add(separator);
        }

        return questionBox;
    }


    static ArrayList<TakeQuiz> getTakeQuizList() {
        return takeQuizList;
    }

    static ArrayList<CheckBox> getMultiAnswerList() {
        return multiAnswerList;
    }

    static ArrayList<RadioButton> getSingleAnswerList() {
        return singleAnswerList;
    }
public int getCurrentQuizId(){
        return currentQuizId;
}
}
