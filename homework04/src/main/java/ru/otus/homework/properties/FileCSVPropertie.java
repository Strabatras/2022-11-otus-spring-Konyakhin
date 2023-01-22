package ru.otus.homework.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "quiz.file.csv")
public class FileCSVPropertie {
    private String folder;

    private String fileName;
}