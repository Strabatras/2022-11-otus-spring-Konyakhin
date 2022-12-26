package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.InterviewAnswerDao;
import ru.otus.homework.domain.InterviewAnswer;
import ru.otus.homework.service.InterviewAnswerService;

@RequiredArgsConstructor
@Service
public class InterviewAnswerServiceImpl implements InterviewAnswerService {
    private final InterviewAnswerDao interviewAnswerDao;

    @Override
    public InterviewAnswer createInterviewAnswer(String interviewAnswer) {
        return interviewAnswerDao.createInterviewAnswer(interviewAnswer);
    }
}