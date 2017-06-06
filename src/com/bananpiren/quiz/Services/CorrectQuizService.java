package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.CorrectQuiz;
import com.bananpiren.quiz.Entity.User;
import com.bananpiren.quiz.Entity.UserQuiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

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
    public List<CorrectQuiz> findAllCorrectQuizByAnswerId(int answerId){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM CorrectQuiz u WHERE u.answerId ="+answerId);
        List<CorrectQuiz> correctQuizList = (List<CorrectQuiz>) query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return correctQuizList;
    }


}
