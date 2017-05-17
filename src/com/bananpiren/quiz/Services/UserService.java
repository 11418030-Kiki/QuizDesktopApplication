package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserService {

    public UserService() {
    }

    //Method to create user
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

    //Getting all users from database and return them in a list as User objects.
    public List findAllUsers() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM  User u");

        return query.getResultList();
    }

    //Get user by ID number
    public User findUserById(int userId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        User user = entityManager.find(User.class, userId);
        System.out.println(user);

        return user;
    }

    //Updating user information
    public void updateUser(int userId, String firstName, String lastName, String userEmail, String userPassword, String userAccountLevel) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, userId);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        user.setAccountLevel(userAccountLevel);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    //Deleting user by user ID
    public void deleteUser(int userId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }
}
