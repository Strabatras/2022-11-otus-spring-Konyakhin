package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.InterviewDao;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class InterviewDaoImpl implements InterviewDao {
    @Override
    public Interview createInterview(Personality personality) {
        return new Interview(personality, new ArrayList<>());
    }
}