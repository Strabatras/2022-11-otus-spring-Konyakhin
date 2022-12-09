package ru.otus.homework;

import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFactory {
    public static final String MESSAGE_I_DONT_HAVE_QUESTIONS = "Sorry. I don't have questions";
    public static final String MESSAGE_ANY_ERROR = "Any error message";
    public static final String MESSAGE_SOMETHING_WENT_WRONG_TO_USER = "Sorry. Something went wrong";
    public static final String MESSAGE_CSV_FILE_IS_NOT_FOUND_TO_USER = "Sorry. The quiz CSV file is not found";
    public static final String MESSAGE_CSV_FILE_PREPARATION_ERROR_TO_USER = "Sorry. Quiz CSV file preparation error";
    public static final String MESSAGE_CSV_FILE_READING_ERROR_TO_USER = "Sorry. Quiz CSV file reading error";
    public static final String MESSAGE_CSV_FILE_IS_NOT_FOUND = "File is not found";
    public static final String MESSAGE_CSV_FILE_IS_EMPTY_FOUND = "File name is empty";
    public static final String CLASS_SIMPLE_NAME_ARRAY_LIST = "ArrayList";
    public static final String CLASS_SIMPLE_NAME_QUIZ = "Quiz";
    public static final String CLASS_SIMPLE_NAME_QUIZ_ANSWER = "QuizAnswer";
    public static final String SOME_TEXT = "Lorem Ipsum is simply dummy text";
    public static final String QUIZ_ANSWER_NAME = "QuizAnswerName";
    public static final String QUIZ_NAME = "QuizName";
    public static final String CSV_EMPTY_NAME_OF_FILE = "";
    public static final String CSV_EMPTY_FILE = "empty.csv";
    public static final String CSV_CORRECT_QUIZZES_FILE = "quizzes.csv";
    public static final String CSV_NON_EXISTENT_FILE = "non-existent.csv";

    public static List<QuizAnswer> answers(String quizName, int quantity) {
        List<QuizAnswer> answers = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            QuizAnswer answer = new QuizAnswer(quizName + 1, (i % 2 == 0));
            answers.add(answer);
        }
        return answers;
    }

    public static List<Quiz> quizzesEmptyList() {
        return new ArrayList<>();
    }

    public static List<Quiz> quizzesWithAnswers() {
        List<Quiz> quizzes = new ArrayList<>();
        List<QuizAnswer> answers = new ArrayList<>();
        quizzes.add(new Quiz("A", answers("A", 3)));
        quizzes.add(new Quiz("B", answers("B", 5)));
        quizzes.add(new Quiz("C", answers("C", 7)));
        quizzes.add(new Quiz("D", answers("D", 0)));
        quizzes.add(new Quiz("E", answers("E", 1)));
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
                new ArrayList<>(Arrays.asList("A")),
                new ArrayList<>(Arrays.asList("B", "B1", "B2", "B3")),
                new ArrayList<>(Arrays.asList("C", "C1", "C2", "C3", "C4", "C5")),
                new ArrayList<>(Arrays.asList("D", "D1")),
                new ArrayList<>(Arrays.asList("E", "E1", "E2"))
        ));
    }
}
