package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.InterviewQuestionAnswerDao;
import ru.otus.homework.domain.InterviewAnswer;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Quiz;

@RequiredArgsConstructor
@Component
public class InterviewQuestionAnswerDaoImpl implements InterviewQuestionAnswerDao {
    @Override
    public InterviewQuestionAnswer createInterviewQuestionAnswer(Quiz quiz, InterviewAnswer interviewAnswer) {
        return new InterviewQuestionAnswer(quiz, interviewAnswer);
    }
}