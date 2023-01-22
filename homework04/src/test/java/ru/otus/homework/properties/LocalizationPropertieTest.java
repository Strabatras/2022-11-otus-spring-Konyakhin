package ru.otus.homework.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Параметры локализации")
@ActiveProfiles()
@SpringBootTest
class LocalizationPropertieTest {

    @Autowired
    private LocalizationPropertie localizationPropertie;

    @DisplayName("возвращает корректное название локали")
    @Test
    void shouldReturnCorrectLocale() {
        assertThat(localizationPropertie.getLocale().getLanguage()).isIn(new String[]{"ru", "en"});
    }
}