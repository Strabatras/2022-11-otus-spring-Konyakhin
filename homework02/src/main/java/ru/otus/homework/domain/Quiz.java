package ru.otus.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Quiz {
    private final String name;
    private final List<QuizAnswer> answers;
}