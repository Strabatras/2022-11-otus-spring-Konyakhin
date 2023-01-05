package ru.otus.homework.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Quiz;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.INTERVIEW_ANSWER;
import static ru.otus.homework.DataFactory.correctQuizWithAnswers;

@DisplayName("Опрос вопросы/ответ - фабрика")
class InterviewQuestionAnswerFactoryTest {

    @DisplayName("возвращается корректно заполненный вопрос со списком ответов")
    @Test
    void shouldReturnCorrectQuiz() {
        Quiz quiz = correctQuizWithAnswers();
        InterviewQuestionAnswer interviewQuestionAnswer
                = (new InterviewQuestionAnswerFactory()).createInterviewQuestionAnswer(quiz, INTERVIEW_ANSWER);
        assertThat(interviewQuestionAnswer.getQuiz()).isEqualTo(quiz);
    }

    @DisplayName("возвращается корректно заполненный ответ")
    @Test
    void shouldReturnCorrectInterviewAnswer() {
        InterviewQuestionAnswer interviewQuestionAnswer
                = (new InterviewQuestionAnswerFactory()).createInterviewQuestionAnswer(correctQuizWithAnswers(), INTERVIEW_ANSWER);
        assertThat(interviewQuestionAnswer.getInterviewAnswer()).isEqualTo(INTERVIEW_ANSWER);
    }
}