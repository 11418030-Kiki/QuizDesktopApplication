package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class FindUserService {

    public FindUserService() {
    }

    //Getting all users from database and return them in a list as User objects.
    public List<User> findAllUsers() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM User u");

        return query.getResultList();
    }
}