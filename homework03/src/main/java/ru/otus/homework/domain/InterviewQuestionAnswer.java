package ru.otus.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InterviewQuestionAnswer {
    private final Quiz quiz;
    private final String interviewAnswer;
}