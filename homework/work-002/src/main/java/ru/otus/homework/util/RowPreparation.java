package ru.otus.homework.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.exception.LineValidationQuizException;

import java.util.List;
import java.util.stream.Collectors;

public class RowPreparation {
    public static boolean isNotEmptyRow(List<String> row) {
        return CollectionUtils.isNotEmpty(row) && StringUtils.isNotBlank(row.get(0));
    }

    public static Quiz rowToQuiz(List<String> row) {
        if (!isNotEmptyRow(row)) {
            throw new LineValidationQuizException("Data has empty lines");
        }
        final List<QuizAnswer> answers = row.stream()
                .skip(1)
                .filter(StringUtils::isNotBlank)
                .map(RowPreparation::rowToQuizAnswer)
                .collect(Collectors.toList());
        return new Quiz(row.get(0).trim(), answers);
    }

    private static String correctAnswer(String answer, QuizAnswer quizAnswer) {
        if (answer.equals("true")) {
            return quizAnswer.getName();
        }
        return answer;
    }

    private static QuizAnswer rowToQuizAnswer(String answer) {
        final String[] splittedAnswer = answer.split(":::");
        final QuizAnswer quizAnswer = new QuizAnswer(splittedAnswer[0].trim());
        if (splittedAnswer.length == 2 && StringUtils.isNotBlank(splittedAnswer[1])) {
            quizAnswer.setCorrectAnswer(correctAnswer(splittedAnswer[1].trim(), quizAnswer));
        }
        return quizAnswer;
    }
}