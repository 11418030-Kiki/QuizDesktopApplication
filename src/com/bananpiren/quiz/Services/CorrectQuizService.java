package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.CorrectQuiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CorrectQuizService {

    public CorrectQuizService() {

    }

    public void correctQuiz(CorrectQuiz correctQuiz) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // starta överföringen
        entityManager.getTransaction().begin();

        // sparar
        entityManager.persist(correctQuiz);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }


}
