package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.InterviewDao;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.service.InterviewService;

@RequiredArgsConstructor
@Service
public class InterviewServiceImpl implements InterviewService {
    private final InterviewDao interviewDao;

    @Override
    public Interview createInterview(Personality personality) {
        return interviewDao.createInterview(personality);
    }
}