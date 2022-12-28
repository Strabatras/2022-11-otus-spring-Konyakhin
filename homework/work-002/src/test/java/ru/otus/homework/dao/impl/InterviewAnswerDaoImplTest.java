package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.dao.InterviewAnswerDao;
import ru.otus.homework.domain.InterviewAnswer;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.INTERVIEW_ANSWER;

@DisplayName("Опрос/Ответы - DAO")
class InterviewAnswerDaoImplTest {

    @DisplayName("возвращается корректный текст ответа")
    @Test
    void shouldReturnCorrectAnswerText() {
        final InterviewAnswerDao interviewAnswerDao = new InterviewAnswerDaoImpl();
        final InterviewAnswer interviewAnswer = interviewAnswerDao.createInterviewAnswer(INTERVIEW_ANSWER);
        assertThat(interviewAnswer.getAnswer()).isEqualTo(INTERVIEW_ANSWER);
    }
}