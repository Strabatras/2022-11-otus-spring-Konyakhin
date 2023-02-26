package ru.otus.homework.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.shell")
public class ShellPropertie {
    private Interactive interactive;

    @Getter
    @Setter
    public static class Interactive{
        private boolean enabled;
    }
}