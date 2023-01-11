package ru.otus.homework.factory;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Quiz;

@Component
public class InterviewQuestionAnswerFactory {
    public InterviewQuestionAnswer createInterviewQuestionAnswer(Quiz quiz, String interviewAnswer) {
        return new InterviewQuestionAnswer(quiz, interviewAnswer);
    }
}