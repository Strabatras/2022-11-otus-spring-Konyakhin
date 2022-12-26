package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.InterviewQuestionAnswerDao;
import ru.otus.homework.domain.InterviewAnswer;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.service.InterviewQuestionAnswerService;

@RequiredArgsConstructor
@Service
public class InterviewQuestionAnswerServiceImpl implements InterviewQuestionAnswerService {
    private final InterviewQuestionAnswerDao interviewQuestionAnswerDao;

    @Override
    public InterviewQuestionAnswer createInterviewQuestionAnswer(Quiz quiz, InterviewAnswer interviewAnswer) {
        return interviewQuestionAnswerDao.createInterviewQuestionAnswer(quiz, interviewAnswer);
    }
}