package ru.otus.homework.factory;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;

import java.util.ArrayList;

@Component
public class InterviewFactory {
    public Interview createInterview(Personality personality) {
        return new Interview(personality, new ArrayList<>());
    }
}