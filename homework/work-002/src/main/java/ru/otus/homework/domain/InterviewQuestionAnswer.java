package ru.otus.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class InterviewQuestionAnswer {
    private final Quiz quiz;
    private final String interviewAnswer;
}