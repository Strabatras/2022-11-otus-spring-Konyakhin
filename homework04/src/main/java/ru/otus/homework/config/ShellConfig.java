package ru.otus.homework.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.properties.ShellPropertie;

@EnableConfigurationProperties({ShellPropertie.class})
@Configuration
public class ShellConfig {
}