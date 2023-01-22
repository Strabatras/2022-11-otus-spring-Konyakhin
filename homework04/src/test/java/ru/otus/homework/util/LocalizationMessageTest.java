package ru.otus.homework.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.homework.properties.LocalizationPropertie;

import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.localeNames;

@DisplayName("Локализация сообщений")
@SpringBootTest
class LocalizationMessageTest {

    @Autowired
    private LocalizationPropertie localizationPropertie;
    @Autowired
    private MessageSource messageSource;

    @DisplayName("наличие параметра identify.yourself для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyYourselfValue(String localeName){
        String value = localizationMessage(localeName).getMessage("identify.yourself");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра identify.name для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyNameValue(String localeName){
        String value = localizationMessage(localeName).getMessage("identify.name");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра identify.surname для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertySurnameValue(String localeName){
        String value = localizationMessage(localeName).getMessage("identify.surname");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра identify.name.cant.be.empty для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyNameCantBeEmptyValue(String localeName){
        String value = localizationMessage(localeName).getMessage("identify.name.cant.be.empty");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра identify.surname.cant.be.empty для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertySurnameCantBeEmptyValue(String localeName){
        String value = localizationMessage(localeName).getMessage("identify.surname.cant.be.empty");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра interview.personality.is.finished для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyPersonalityIsFinishedValue(String localeName){
        String value = localizationMessage(localeName).getMessage("interview.personality.is.finished");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра interview.answered.of.questions для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyAnsweredOfQuestionsValue(String localeName){
        String value = localizationMessage(localeName).getMessage("interview.answered.of.questions");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра interview.answered.of.correct для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyAnsweredOfCorrectValue(String localeName){
        String value = localizationMessage(localeName).getMessage("interview.answered.of.correct");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра error.message.application.configuration.error для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyMessageApplicationConfigurationErrorValue(String localeName){
        String value = localizationMessage(localeName).getMessage("error.message.application.configuration.error");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра error.message.invalid.data.format для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyMessageInvalidDataFormatValue(String localeName){
        String value = localizationMessage(localeName).getMessage("error.message.invalid.data.format");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра error.message.error.reading.data для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyMessageErrorReadingDataValue(String localeName){
        String value = localizationMessage(localeName).getMessage("error.message.error.reading.data");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра error.message.i.dont.have.questions для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyMessageDontHaveQuestionsValue(String localeName){
        String value = localizationMessage(localeName).getMessage("error.message.i.dont.have.questions");
        assertThat(value).isNotBlank();
    }

    @DisplayName("наличие параметра error.message.for.unhandled.exception для языка локализации")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("argsProviderLocaleNameFactory")
    void shouldReturnPropertyMessageForUnhandledExceptionValue(String localeName){
        String value = localizationMessage(localeName).getMessage("error.message.for.unhandled.exception");
        assertThat(value).isNotBlank();
    }

    private Localization localizationMessage(String localeName){
        localizationPropertie.setLocale(new Locale(localeName));
        return new LocalizationMessage(messageSource, localizationPropertie);
    }

    static Stream<String> argsProviderLocaleNameFactory(){
        return localeNames();
    }
}