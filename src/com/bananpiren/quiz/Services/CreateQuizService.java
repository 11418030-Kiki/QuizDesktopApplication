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


//    private ArrayList<QuizQuestions> quizQuestionsList = new ArrayList<>();
//    private ArrayList<QuestionAnswers> questionAnswersList = new ArrayList<>();

    public CreateQuizService() {
    }

//    // Creating the QuestionAnswersObjects and adding them to a list
//    public void addQuizAnswerObject(String answer, boolean correct) {
//        QuestionAnswers qa = new QuestionAnswers();
//        qa.setAnswer(answer);
//        qa.setCorrectAnswer(correct);
//        questionAnswersList.add(qa);
//    }
//
//    // Creating the QuizQuestionsObjects and adding them to a list
//    public void addQuizQuestionObject(String question) {
//        QuizQuestions q = new QuizQuestions();
//        q.setQuestion(question);
//        quizQuestionsList.add(q);
//    }

    public void createQuiz(String quizName, int timeLimit, LocalDate quizStartDate, LocalDate quizEndDate, ArrayList<QuizQuestions> qList, ArrayList<QuestionAnswers> aList) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLink_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

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
        for (QuizQuestions qq : qList) {
            entityManager.persist(qq);

//            System.out.println("qa.getQuestion = " + qq.getQuestion() + "\n\n");
//
//            for(int i = 0; i < 4; i++) {
//                // adding the QuestionAnswersObjects to every QuizQuestionEntity
//                qq.setAnswerList(aList);
//                entityManager.persist(aList.get(i));
//            }
            // adding the QuestionAnswersObjects to every QuizQuestionEntity
            qq.setAnswerList(aList);

        }

        // persist QuestionAnswers entity
        for(QuestionAnswers qa : aList) {
            entityManager.persist(qa);

            System.out.println("qa.getAnswer() = " + qa.getAnswer() + "\n\n");


        }


// skickar in
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
