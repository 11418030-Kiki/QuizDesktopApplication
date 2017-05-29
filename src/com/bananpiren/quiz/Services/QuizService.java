package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.QuestionAnswers;
import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.QuizQuestions;
import com.bananpiren.quiz.Entity.TakeQuiz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class QuizService {

    public QuizService() {}

    public static void create(Quiz quiz) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(quiz);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void create(Quiz quiz, ArrayList<QuizQuestions> questions, ArrayList<QuestionAnswers> answers) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(quiz);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        QuestionService.create(questions);
        AnswerService.create(answers);
    }

    // Delete a quiz by ID
    public void deleteQuiz(int quizId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Quiz quiz = entityManager.find(Quiz.class, quizId);
        entityManager.remove(quiz);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    // Get all Quizes from database and return as list of Quiz objects
    public List<Quiz> findAllQuiz() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT q FROM Quiz q");

        return query.getResultList();
    }

    public ArrayList<TakeQuiz> currentQuiz(int currentQuizId) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // Query question and put it in a list
        Query quizName = entityManager.createQuery("SELECT qq.quiz.quizName FROM QuizQuestions qq JOIN QuestionAnswers qa WHERE qq.questionId = qa.question.questionId AND qq.quiz.quizId = " + currentQuizId + " ");
        List quizNameList = quizName.getResultList();

        // Query question and put them in a list
        Query question = entityManager.createQuery("SELECT qq.question FROM QuizQuestions qq JOIN QuestionAnswers qa WHERE qq.questionId = qa.question.questionId AND qq.quiz.quizId = " + currentQuizId + "");
        List questionList = question.getResultList();

        // Query answers and put them in a list
        Query answer = entityManager.createQuery("SELECT qa.answer FROM QuizQuestions qq JOIN QuestionAnswers qa WHERE qq.questionId = qa.question.questionId AND qq.quiz.quizId = " + currentQuizId + "");
        List answerList = answer.getResultList();

        // Query questionType and put them in a list
        // Query answers and put them in a list
        Query questionType = entityManager.createQuery("SELECT qa.question.questionType FROM QuizQuestions qq JOIN QuestionAnswers qa WHERE qq.questionId = qa.question.questionId AND qq.quiz.quizId = " + currentQuizId + "");
        List questionTypeList = questionType.getResultList();

        int count = 0;

        TakeQuiz takeQuiz;
        ArrayList<TakeQuiz> takeQuizList = new ArrayList<>();

        // skapar TakeQuiz med namn, frågor och svar
        for(int i = 0; i < quizNameList.size(); i++) {
            takeQuiz = new TakeQuiz();
            takeQuiz.setQuizName(quizNameList.get(i).toString());
            takeQuiz.setQuestion(questionList.get(i).toString());
            takeQuiz.setAnswer(answerList.get(i).toString());
            takeQuiz.setQuestionType(questionTypeList.get(i).toString());

            takeQuizList.add(takeQuiz);
        }

        entityManager.close();
        entityManagerFactory.close();

        return takeQuizList;
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
