package ru.otus.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.properties.FileCSVPropertie;
import ru.otus.homework.properties.LocalizationPropertie;
import ru.otus.homework.util.CsvFileDataReader;
import ru.otus.homework.util.DataReader;
import ru.otus.homework.util.Localization;
import ru.otus.homework.util.LocalizationMessage;


@RequiredArgsConstructor
@EnableConfigurationProperties({FileCSVPropertie.class, LocalizationPropertie.class})
@Configuration
public class UtilConfig {
    private final FileCSVPropertie fileCSVPropertie;
    private final LocalizationPropertie localizationPropertie;
    private final MessageSource messageSource;

    @Bean
    public DataReader dataReader() {
        final String quizFile = fileCSVPropertie.getFolder() + '/' + localizationPropertie.getLocale().toString() + '/' + fileCSVPropertie.getFileName();
        return new CsvFileDataReader(quizFile);
    }

    @Bean
    public Localization localization() {
        return new LocalizationMessage(messageSource, localizationPropertie);
    }
}