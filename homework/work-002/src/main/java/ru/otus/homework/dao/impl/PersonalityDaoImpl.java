package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.PersonalityDao;
import ru.otus.homework.domain.Personality;

@RequiredArgsConstructor
@Component
public class PersonalityDaoImpl implements PersonalityDao {
    @Override
    public Personality createPersonality(String name, String surname) {
        return new Personality(name, surname);
    }
}