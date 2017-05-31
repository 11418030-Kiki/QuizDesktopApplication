package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.CorrectQuiz;
import com.bananpiren.quiz.Entity.UserQuiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserQuizService {

    public UserQuizService() {

    }

    public void userQuiz(UserQuiz userQuiz) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // starta överföringen
        entityManager.getTransaction().begin();

        // sparar
        entityManager.persist(userQuiz);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
    public List<UserQuiz> getAllUserQuizById(int quizID){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM UserQuiz u WHERE u.quizId ="+quizID);
        List<UserQuiz> userQuiz = (List<UserQuiz>) query.getResultList();

        return userQuiz;

    }


}
