package ru.otus.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.util.CsvFileDataReader;
import ru.otus.homework.util.DataReader;

@PropertySource("classpath:properties/application.properties")
@Configuration
public class UtilConfig {

    @Bean
    public DataReader dataReader(@Value("${file.csv.file.name}") String fileName) {
        return new CsvFileDataReader(fileName);
    }
}