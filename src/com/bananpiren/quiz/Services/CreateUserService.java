package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ${Eric} on 2017-05-10.
 */
public class CreateUserService {

    public void createUser(String firstName, String lastName, String email, String password, String accountLevel) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAccountLevel(accountLevel);

        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public CreateUserService() {
    }
}
