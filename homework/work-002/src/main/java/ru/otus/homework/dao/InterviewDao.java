package ru.otus.homework.dao;

import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;

public interface InterviewDao {
    Interview createInterview(Personality personality);
}