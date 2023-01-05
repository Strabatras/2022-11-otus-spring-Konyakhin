package ru.otus.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuizAnswer {
    private final String name;
    private String correctAnswer;
}