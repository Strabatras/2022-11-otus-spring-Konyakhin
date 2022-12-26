package ru.otus.homework;

import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFactory {
    public static final String MESSAGE_I_DONT_HAVE_QUESTIONS = "Sorry. I don't have questions.";
    public static final String MESSAGE_ANY_ERROR = "Any error message.";
    public static final String MESSAGE_CSV_FILE_IS_NOT_FOUND = "File not found";
    public static final String MESSAGE_CSV_FILE_IS_EMPTY_FOUND = "File name is empty";
    public static final String CLASS_SIMPLE_NAME_ARRAY_LIST = "ArrayList";
    public static final String CLASS_SIMPLE_NAME_QUIZ = "Quiz";
    public static final String CLASS_SIMPLE_NAME_QUIZ_ANSWER = "QuizAnswer";
    public static final String SOME_TEXT = "Lorem Ipsum is simply dummy text.";
    public static final String QUIZ_ANSWER_NAME = "QuizAnswerName";
    public static final String QUIZ_NAME = "QuizName";
    public static final String CSV_EMPTY_NAME_OF_FILE = "";
    public static final String CSV_EMPTY_FILE = "empty.csv";
    public static final String CSV_CORRECT_QUIZZES_FILE = "quizzes.csv";
    public static final String CSV_NON_EXISTENT_FILE = "non-existent.csv";
    public static final String INTERVIEWEE_NAME = "INTERVIEWEE_NAME";
    public static final String INTERVIEWEE_SURNAME = "INTERVIEWEE_SURNAME";

    public static List<QuizAnswer> answers(String quizName, int quantity) {
        List<QuizAnswer> answers = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            answers.add(new QuizAnswer(quizName + 1));
        }
        return answers;
    }

    public static List<QuizAnswer> correctAnswers(String quizName, int... numbers) {
        List<QuizAnswer> answers = new ArrayList<>();
        for (int number : numbers) {
            answers.add(new QuizAnswer(quizName + number));
        }
        return answers;
    }

    public static List<Quiz> quizzesEmptyList() {
        return new ArrayList<>();
    }

    public static List<QuizAnswer> answersEmptyList() {
        return new ArrayList<>();
    }

    public static List<QuizAnswer> correctAnswersEmptyList() {
        return new ArrayList<>();
    }

    public static List<Quiz> quizzesWithAnswers() {
        List<Quiz> quizzes = new ArrayList<>();
        List<QuizAnswer> answers = new ArrayList<>();
        quizzes.add(new Quiz("A", answers("A", 3), correctAnswers("A", 2)));
        quizzes.add(new Quiz("B", answers("B", 5), correctAnswers("B", 1, 3)));
        quizzes.add(new Quiz("C", answers("C", 7), correctAnswers("B", 2, 5, 7)));
        quizzes.add(new Quiz("D", answers("D", 0), correctAnswersEmptyList()));
        quizzes.add(new Quiz("E", answers("E", 1), correctAnswersEmptyList()));
        return quizzes;
    }

    public static List<List<String>> preparedLinesFromFileWithEmptyLines() {
        return new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList("A", "A1", "A2", "A3")),
                new ArrayList<>(Arrays.asList("B")),
                new ArrayList<>(Arrays.asList(" ")),
                new ArrayList<>(Arrays.asList("C", "C1", "C2", "C3", "C4", "C5")),
                new ArrayList<>(Arrays.asList("D", "D1", "D2")),
                new ArrayList<>(Arrays.asList(""))
        ));
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

    public static List<Quiz> expectedQuizzesForCorrectQuizzesFile() {
        List<Quiz> quizzes = new ArrayList<>();
        List<QuizAnswer> answers = new ArrayList<>();
        quizzes.add(new Quiz("A", answersEmptyList(), correctAnswersEmptyList()));
        quizzes.add(new Quiz("B", answers("B", 3), correctAnswersEmptyList()));
        quizzes.add(new Quiz("C", answers("C", 5), correctAnswersEmptyList()));
        quizzes.add(new Quiz("D", answers("D", 1), correctAnswersEmptyList()));
        quizzes.add(new Quiz("E", answers("E", 2), correctAnswersEmptyList()));
        return quizzes;
    }
}
