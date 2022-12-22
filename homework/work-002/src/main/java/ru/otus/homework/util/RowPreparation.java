package ru.otus.homework.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.exception.LineValidationQuizException;

import java.util.ArrayList;
import java.util.List;

public class RowPreparation {
    public static boolean isNotEmptyRow(List<String> row) {
        return CollectionUtils.isNotEmpty(row) && StringUtils.isNotBlank(row.get(0));
    }

    public static QuizAnswer stringToQuizAnswer(String answer) {
        return new QuizAnswer(answer);
    }

    private static void prepareQuizAnsqers(String rawAnswerData, List<QuizAnswer> answers, List<QuizAnswer> correctAnswers) {
        final String[] splittedAnswer = rawAnswerData.split(":::");
        QuizAnswer quizAnswer = null;
        if (StringUtils.isNotBlank(splittedAnswer[0])) {
            quizAnswer = new QuizAnswer(splittedAnswer[0].trim());
            answers.add(quizAnswer);
        }
        if (splittedAnswer.length == 2 && StringUtils.isNotBlank(splittedAnswer[1])) {
            final String answer = splittedAnswer[1].trim();
            if (quizAnswer != null && answer.equals("true")) {
                correctAnswers.add(stringToQuizAnswer(quizAnswer.getName()));
            }
            if (quizAnswer == null && !answer.equals("true")) {
                correctAnswers.add(stringToQuizAnswer(answer));
            }
        }
    }

    public static Quiz rowToQuiz(List<String> row) {
        if (!isNotEmptyRow(row)) {
            throw new LineValidationQuizException("Data has empty lines");
        }
        final List<QuizAnswer> answers = new ArrayList<>();
        final List<QuizAnswer> correctAnswers = new ArrayList<>();
        row.stream()
                .skip(1)
                .filter(StringUtils::isNotBlank)
                .forEach(rawAnswerData -> prepareQuizAnsqers(rawAnswerData, answers, correctAnswers));
        return new Quiz(row.get(0).trim(), answers, correctAnswers);
    }
}