package ru.otus.homework.service;

import ru.otus.homework.domain.Personality;

public interface PersonalityService {
    Personality createPersonality(String name, String surname);
}