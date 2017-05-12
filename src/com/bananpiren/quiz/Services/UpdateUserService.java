package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Anton on 2017-05-11.
 */
public class UpdateUserService {
    public void updateEmail(String newEmail, String searchAgainstID){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, searchAgainstID);
        user.setEmail(newEmail);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
    public void updatePassword(String newPassword, String searchAgainstID){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, searchAgainstID);
        user.setPassword(newPassword);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
    public void update(){

    }
}