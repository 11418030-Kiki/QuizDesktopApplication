package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.QuestionAnswers;
import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.QuizQuestions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateQuizService {

    private ArrayList<QuizQuestions> quizQuestionsList = new ArrayList<>();
    private ArrayList<QuestionAnswers> questionAnswersList = new ArrayList<>();

    public CreateQuizService() {
    }

    public void addQuizQuestionObject(String question) {
        QuizQuestions q = new QuizQuestions();
        q.setQuestion(question);
        q.setAnswerList(questionAnswersList);
        quizQuestionsList.add(q);
    }

    // save every answer and correct marker
    public void addQuizAnswerObject(String answer, boolean correct) {
        QuestionAnswers qa = new QuestionAnswers();
        qa.setAnswer(answer);
        qa.setCorrectAnswer(correct);
        questionAnswersList.add(qa);
    }

    public void createQuiz(String quizName, int timeLimit, LocalDate quizStartDate, LocalDate quizEndDate) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // persist QuizQuestions entity
        for (QuizQuestions qq : quizQuestionsList) {
            entityManager.persist(qq);
        }

        // persist QuestionAnswers entity
        for (QuestionAnswers qa : questionAnswersList) {
            entityManager.persist(qa);
        }

        // create quiz entity oneToMany
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizName);
        quiz.setTimeLimit(timeLimit);
        quiz.setQuizStartDate(quizStartDate.toString());
        quiz.setQuizEndDate(quizEndDate.toString());
        quiz.setQuestionsList(quizQuestionsList);

        // persist quiz
        entityManager.persist(quiz);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

//        // nollställ listorna för nästa quiz
//        quizQuestionsList.clear();
//        quizQuestionsList.clear();

    }
}
