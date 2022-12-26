package ru.otus.homework.dao;

import ru.otus.homework.domain.Personality;

public interface PersonalityDao {
    Personality createPersonality(String name, String surname);
}
