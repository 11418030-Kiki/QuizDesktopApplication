package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.Quiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class QuizService {

    public QuizService() {}

    // Delete a quiz by ID
    public void deleteQuiz(int quizId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Quiz quiz = entityManager.find(Quiz.class, quizId);

        entityManager.remove(quiz);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Get all Quizes from database and return as list of Quiz objects
    public List<Quiz> findAllQuiz() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT q FROM Quiz q");

        return query.getResultList();
    }

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
