package ru.otus.homework.service;

import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;

public interface InterviewService {
    Interview createInterview(Personality personality);
}