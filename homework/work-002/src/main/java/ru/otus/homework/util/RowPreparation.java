package ru.otus.homework.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.exception.FileEmptyLineQuizException;

import java.util.List;
import java.util.stream.Collectors;

public class RowPreparation {

    public static boolean isNotEmptyRow(List<String> row) {
        return CollectionUtils.isNotEmpty(row) && StringUtils.isNotBlank(row.get(0));
    }

    public static boolean isNotEmptyRowOrElseThrowException(List<String> row) {
        if (!RowPreparation.isNotEmptyRow(row)) {
            throw new FileEmptyLineQuizException("File has empty lines");
        }
        return true;
    }

    public static QuizAnswer stringToQuizAnswer(String answer) {
        return new QuizAnswer(answer.trim(), false);
    }

    public static List<QuizAnswer> rowToAnswers(List<String> row) {
        return row.stream()
                .skip(1)
                .filter(StringUtils::isNotBlank)
                .map(RowPreparation::stringToQuizAnswer)
                .collect(Collectors.toList());
    }

    public static Quiz rowToQuiz(List<String> row) {
        return new Quiz(row.get(0).trim(), RowPreparation.rowToAnswers(row));
    }
}