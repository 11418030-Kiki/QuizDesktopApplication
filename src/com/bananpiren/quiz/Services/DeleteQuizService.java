package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.Quiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeleteQuizService {

    public void deleteQuiz(int quizId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Quiz quiz = entityManager.find(Quiz.class, quizId);

        entityManager.remove(quiz);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}