package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class FindQuizService {

    public FindQuizService() {
    }

    //Getting all quizes from database and return them in a list as Quiz objects
    public List<Quiz> findAllQuize() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT q FROM  Quiz q");

        return query.getResultList();
    }
}