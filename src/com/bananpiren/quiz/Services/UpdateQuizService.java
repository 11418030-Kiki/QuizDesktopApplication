package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.Quiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UpdateQuizService {

    public void updateQuiz(int quizId, String quizName, int timeLimit, String quizStartDate, String quizEndDate) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Quiz quiz = entityManager.find(Quiz.class, quizId);

        quiz.setQuizName(quizName);
        quiz.setTimeLimit(timeLimit);
        quiz.setQuizStartDate(quizStartDate);
        quiz.setQuizEndDate(quizEndDate);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}