package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.PersonalityDao;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.service.PersonalityService;

@RequiredArgsConstructor
@Service
public class PersonalityServiceImpl implements PersonalityService {
    private final PersonalityDao personalityDao;

    @Override
    public Personality createPersonality(String name, String surname) {
        return personalityDao.createPersonality(name, surname);
    }
}