package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.dao.InterviewQuestionAnswerDao;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.QuizAnswer;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.correctQuizWithAnswers;
import static ru.otus.homework.DataFactory.interviewAnswer;

@DisplayName("Сервис опрос вопросы/ответ")
@ExtendWith(MockitoExtension.class)
class InterviewQuestionAnswerServiceImplTest {

    @Mock
    private InterviewQuestionAnswerDao interviewQuestionAnswerDao;

    @InjectMocks
    private InterviewQuestionAnswerServiceImpl interviewQuestionAnswerService;

    @DisplayName("возвращаются корректно созданный экземпляр класса с данными опроса и ответами к нему")
    @Test
    void shouldCreateCorrectInterviewQuestionAnswer() {
        when(interviewQuestionAnswerDao.createInterviewQuestionAnswer(any(), any()))
                .thenReturn(new InterviewQuestionAnswer(correctQuizWithAnswers(), interviewAnswer()));
        assertThat(interviewQuestionAnswerService.createInterviewQuestionAnswer(any(), any()))
                .extracting(toQuizName -> toQuizName.getQuiz().getName(),
                        toQuizAnswers -> toQuizAnswers.getQuiz().getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        toQuizCorrectAnswers -> toQuizCorrectAnswers.getQuiz().getCorrectAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        toInterviewAnswer -> toInterviewAnswer.getInterviewAnswer().getAnswer()
                )
                .containsExactly("Q", Arrays.asList("Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7"), Arrays.asList("Q3", "Q5", "Q6"), "Interview Answer");
        verify(interviewQuestionAnswerDao, times(1)).createInterviewQuestionAnswer(any(), any());
    }
}