package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.QuizQuestions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateQuizService {


    private ArrayList<QuizQuestions> quizQuestionsList = new ArrayList<>();

    public CreateQuizService() {
    }

    public void addQuizQuestionObject(String question) {
        QuizQuestions q = new QuizQuestions();
        q.setQuestion(question);
        quizQuestionsList.add(q);
    }

    public void createQuiz(String quizName, int timeLimit, LocalDate quizStartDate, LocalDate quizEndDate) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        for (QuizQuestions qq : quizQuestionsList) {
            entityManager.persist(qq);
        }

        // create quiz entity
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizName);
        quiz.setTimeLimit(timeLimit);
        quiz.setQuizStartDate(quizStartDate.toString());
        quiz.setQuizEndDate(quizEndDate.toString());
        quiz.setQuestionsList(quizQuestionsList);

        // store quiz
        entityManager.persist(quiz);


        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
