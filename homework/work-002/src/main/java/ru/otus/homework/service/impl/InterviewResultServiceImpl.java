package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.InterviewResultService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static ru.otus.homework.util.InterviewStatistic.hasQuizCorrectAnswer;

@RequiredArgsConstructor
@Service
public class InterviewResultServiceImpl implements InterviewResultService {
    private final IOService ioService;

    @Override
    public void printStatistic(Interview interview) {
        final Personality personality = interview.getPersonality();
        ioService.outputString("\n" + personality.getName() + " " + personality.getSurname() + ", quiz is finished.");

        final List<InterviewQuestionAnswer> interviewQuestionAnswerList = interview.getInterviewQuestionAnswers();
        int interviewAnswerCount = 0;
        int interviewCorrectAnswerCount = 0;
        int quizzesWithCorrectAnswerCount = 0;

        for(InterviewQuestionAnswer interviewQuestionAnswer : interviewQuestionAnswerList){
            if (interviewQuestionAnswer.getQuiz().getCorrectAnswers().size() > 0){
                quizzesWithCorrectAnswerCount++;
            }
            String interviewAnswer = interviewQuestionAnswer.getInterviewAnswer().getAnswer();
            if (isNotEmpty(interviewAnswer) && isNotBlank(interviewAnswer)) {
                if (hasQuizCorrectAnswer(interviewQuestionAnswer.getQuiz(), interviewAnswer.trim())){
                    interviewCorrectAnswerCount++;
                }
                interviewAnswerCount++;
            }
        }
        ioService.outputString(
                    format("You have answered %s of %s questions.", interviewAnswerCount, interviewQuestionAnswerList.size())
        );
        ioService.outputString(
                format("Questions with possibility of correct answers - %s. Correct answers - %s.", quizzesWithCorrectAnswerCount, interviewCorrectAnswerCount)
        );
    }
}