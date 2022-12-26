package ru.otus.homework.dao;

import ru.otus.homework.domain.InterviewAnswer;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Quiz;

public interface InterviewQuestionAnswerDao {
    InterviewQuestionAnswer createInterviewQuestionAnswer(Quiz quiz, InterviewAnswer interviewAnswer);
}