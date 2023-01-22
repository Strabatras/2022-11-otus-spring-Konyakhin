package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.InterviewResultService;
import ru.otus.homework.util.InterviewStatistic;
import ru.otus.homework.util.Localization;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@RequiredArgsConstructor
@Service
public class InterviewResultServiceImpl implements InterviewResultService {
    private final IOService ioService;
    private final InterviewStatistic interviewStatistic;
    private final Localization localization;

    @Override
    public void printStatistic(Interview interview) {
        final Personality personality = interview.getPersonality();
        final String interviewPersonalityIsFinished
                = localization.getMessage("interview.personality.is.finished", new String[]{personality.getName(), personality.getSurname()});
        ioService.outputString("\n" + interviewPersonalityIsFinished);

        final List<InterviewQuestionAnswer> interviewQuestionAnswerList = interview.getInterviewQuestionAnswers();
        int interviewAnswerCount = 0;
        int interviewCorrectAnswerCount = 0;
        int quizzesWithCorrectAnswerCount = 0;

        for (InterviewQuestionAnswer interviewQuestionAnswer : interviewQuestionAnswerList) {
            if (interviewStatistic.hasQuizCorrectAnswer(interviewQuestionAnswer.getQuiz())) {
                quizzesWithCorrectAnswerCount++;
            }
            if (isNotEmpty(interviewQuestionAnswer.getInterviewAnswer()) && isNotBlank(interviewQuestionAnswer.getInterviewAnswer())) {
                if (interviewStatistic.isQuizCorrectAnswer(interviewQuestionAnswer.getQuiz(), interviewQuestionAnswer.getInterviewAnswer().trim())) {
                    interviewCorrectAnswerCount++;
                }
                interviewAnswerCount++;
            }
        }
        final String interviewAnsweredOfQuestions
                = localization.getMessage("interview.answered.of.questions", new Integer[]{interviewAnswerCount, interviewQuestionAnswerList.size()});
        ioService.outputString(interviewAnsweredOfQuestions);

        final String interviewAnsweredOfCorrect
                = localization.getMessage("interview.answered.of.correct", new Integer[]{quizzesWithCorrectAnswerCount, interviewCorrectAnswerCount});
        ioService.outputString(interviewAnsweredOfCorrect);
    }
}