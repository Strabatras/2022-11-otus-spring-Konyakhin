package ru.otus.homework.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Getter
@Setter
@ConfigurationProperties(prefix = "quiz.localization")
public class LocalizationPropertie {
    private Locale locale;
}