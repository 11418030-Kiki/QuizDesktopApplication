package com.bananpiren.quiz.Services;

import com.bananpiren.quiz.Entity.QuestionAnswers;
import com.bananpiren.quiz.Entity.Quiz;
import com.bananpiren.quiz.Entity.QuizQuestions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateQuizService {

    private ArrayList<QuizQuestions> qList = new ArrayList<>();
    private ArrayList<QuestionAnswers> aList = new ArrayList<>();

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    private boolean cl = false;

    public CreateQuizService() {
        entityManager.getTransaction().begin();
        System.out.println("\n\n STARTA \n\n ");
    }

    // Creating the QuestionAnswersObjects and adding them to a list
    public void addQuizAnswerObject(String answer, boolean correct) {
        QuestionAnswers qa = new QuestionAnswers();
        qa.setAnswer(answer);
        qa.setCorrectAnswer(correct);

        // persista svaren
        entityManager.persist(qa);

        aList.add(qa);

    }

    // Creating the QuizQuestionsObjects and adding them to a list
    public void addQuizQuestionObject(String question) {
        QuizQuestions q = new QuizQuestions();
        q.setQuestion(question);

        // persista frågan
        entityManager.persist(q);
        qList.add(q);

        q.setAnswerList(aList);
        aList.clear();

    }


    public void createQuiz(String quizName, int timeLimit, LocalDate quizStartDate, LocalDate quizEndDate) {

        // create quiz entity oneToMany
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizName);
        quiz.setTimeLimit(timeLimit);
        quiz.setQuizStartDate(quizStartDate.toString());
        quiz.setQuizEndDate(quizEndDate.toString());
        quiz.setQuestionsList(qList);

        // persist quiz
        entityManager.persist(quiz);


        // varje gång du länger in fråga persist direkt
        // persist QuizQuestions entity and create OneToMany
//        for (QuizQuestions qq : qList) {
////            entityManager.persist(qq);
//
////            System.out.println("qq.getQuestion = " + qq.getQuestion() + "\n\n");
//
////            for(int i = 0; i < 4; i++) {
//////                // adding the QuestionAnswersObjects to every QuizQuestionEntity
////                qq.setAnswerList(aList);
////                entityManager.persist(aList.get(i));
////
//////                entityManager.persist(qa);
//////
////            }
//            // adding the QuestionAnswersObjects to every QuizQuestionEntity
//            qq.setAnswerList(aList);
//
//        }
//        for (QuestionAnswers q : aList) {
//            System.out.println("\n\n ANSWER = " + q.getAnswer() + "\n\n ");
//        }
//
//        for (QuizQuestions qq : qList) {
//            System.out.println("\n\n QUESTION = " + qq.getQuestion() + "\n\n ");
//        }


//        // persist QuestionAnswers entity
//        for(QuestionAnswers qa : aList) {
//            entityManager.persist(qa);
//
//            System.out.println("qa.getAnswer() = " + qa.getAnswer() + "\n\n");
//
//
//        }


// skickar in
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
