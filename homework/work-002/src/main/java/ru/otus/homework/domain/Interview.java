package ru.otus.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Interview {
    private final Personality personality;
    private final List<InterviewQuestionAnswer> interviewQuestionAnswers;

    public void setQuestionAnswer(InterviewQuestionAnswer interviewQuestionAnswer){
        interviewQuestionAnswers.add(interviewQuestionAnswer);
    }
}