package ru.otus.homework;

import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFactory {
    public static final String MESSAGE_ANY_ERROR = "Any error message.";
    public static final String MESSAGE_FILE_IS_NOT_FOUND = "File not found";
    public static final String MESSAGE_FILE_IS_EMPTY_FOUND = "File name is empty";
    public static final String CLASS_SIMPLE_NAME_ARRAY_LIST = "ArrayList";
    public static final String SOME_TEXT = "Lorem Ipsum is simply dummy text.";
    public static final String QUIZ_ANSWER_NAME = "QuizAnswerName";
    public static final String QUIZ_NAME = "QuizName";
    public static final String CSV_EMPTY_NAME_OF_FILE = "";
    public static final String CSV_EMPTY_FILE = "empty.csv";
    public static final String CSV_CORRECT_QUIZZES_FILE = "quizzes.csv";
    public static final String CSV_NON_EXISTENT_FILE = "non-existent.csv";
    public static final String PERSONALITY_NAME = "Personality name";
    public static final String PERSONALITY_SURNAME = "Personality surname";
    public static final String INTERVIEW_ANSWER = "Interview Answer";
    public static final String MESSAGE_APPLICATION_CONFIGURATION_ERROR = "Sorry. Application configuration error.";
    public static final String MESSAGE_INVALID_DATA_FORMAT_QUESTIONS_ERROR = "Sorry. Invalid data format with questions.";
    public static final String MESSAGE_READING_DATA_QUESTIONS_ERROR = "Sorry. Error reading data with questions.";
    public static final String MESSAGE_I_DONT_HAVE_QUESTIONS__ERROR = "Sorry. I don't have questions.";

    public static List<QuizAnswer> answers(String quizName, int quantity) {
        List<QuizAnswer> answers = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            QuizAnswer quizAnswer = new QuizAnswer(quizName + i);
            if(i%2 == 0) {
                quizAnswer.setCorrectAnswer(quizName+i);
            }
            answers.add(quizAnswer);
        }
        return answers;
    }

    public static List<Quiz> quizzesWithAnswers() {
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new Quiz("A", answers("A", 3)));
        quizzes.add(new Quiz("B", answers("B", 5)));
        quizzes.add(new Quiz("C", answers("C", 7)));
        quizzes.add(new Quiz("D", answers("D", 0)));
        quizzes.add(new Quiz("E", answers("E", 1)));
        return quizzes;
    }

    public static List<List<String>> expectedReadLinesForCorrectQuizzesFile() {
        return new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList("A", ":::15")),
                new ArrayList<>(Arrays.asList("B", "B1", "B2", "B3")),
                new ArrayList<>(Arrays.asList("C", "C1", "C2:::true", "C3", "C4:::true", "C5")),
                new ArrayList<>(Arrays.asList("D", "D1")),
                new ArrayList<>(Arrays.asList("E", "E1", "E2"))
        ));
    }

    public static Quiz correctQuizWithAnswers(){
        return new Quiz("Q", answers("Q", 7));
    }

    public static Personality personality(){
        return new Personality(PERSONALITY_NAME, PERSONALITY_SURNAME);
    }
    public static List<InterviewQuestionAnswer> interviewQuestionAnswers(){
        List<InterviewQuestionAnswer> interviewQuestionAnswers = new ArrayList<>();
        List<Quiz> quizzes = quizzesWithAnswers();
        for (Quiz quiz : quizzes){
            interviewQuestionAnswers.add(
                    new InterviewQuestionAnswer(quiz, INTERVIEW_ANSWER + " " + quiz.getName())
            );
        }
        return interviewQuestionAnswers;
    }

    public static InterviewQuestionAnswer interviewQuestionAnswer(){
        return new InterviewQuestionAnswer(correctQuizWithAnswers(), INTERVIEW_ANSWER);
    }
}
