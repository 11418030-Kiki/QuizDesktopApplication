package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.Quiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class CreateQuizService {

    public CreateQuizService() {}

        public void createQuiz(String quizName, int timeLimit, LocalDate quizStartDate, LocalDate quizEndDate) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Quiz quiz = new Quiz();
        quiz.setQuizName(quizName);
        quiz.setTimeLimit(timeLimit);
        quiz.setQuizStartDate(quizStartDate.toString());
        quiz.setQuizEndDate(quizEndDate.toString());

        entityManager.persist(quiz);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
