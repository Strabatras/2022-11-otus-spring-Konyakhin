package ru.otus.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class Quiz {
    private final String name;
    private final List<QuizAnswer> answers;
}