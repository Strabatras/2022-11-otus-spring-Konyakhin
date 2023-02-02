package ru.otus.homework.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;

@Setter
@Getter
@Component
public class ShellQuizRunner {
    private Personality personality;
    private Interview interview;
}
