package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.InterviewAnswerDao;
import ru.otus.homework.domain.InterviewAnswer;

@RequiredArgsConstructor
@Component
public class InterviewAnswerDaoImpl implements InterviewAnswerDao {
    @Override
    public InterviewAnswer createInterviewAnswer(String interviewAnswer) {
        return new InterviewAnswer(interviewAnswer);
    }
}