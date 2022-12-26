package ru.otus.homework.service;

import ru.otus.homework.domain.InterviewAnswer;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Quiz;

public interface InterviewQuestionAnswerService {
    InterviewQuestionAnswer createInterviewQuestionAnswer(Quiz quiz, InterviewAnswer interviewAnswer);
}