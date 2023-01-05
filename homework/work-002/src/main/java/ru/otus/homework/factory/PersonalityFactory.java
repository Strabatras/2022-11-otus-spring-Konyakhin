package ru.otus.homework.factory;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Personality;

@Component
public class PersonalityFactory {
    public Personality createPersonality(String name, String surname) {
        return new Personality(name, surname);
    }
}