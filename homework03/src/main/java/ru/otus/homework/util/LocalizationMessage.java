package ru.otus.homework.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import ru.otus.homework.properties.LocalizationPropertie;

@RequiredArgsConstructor
public class LocalizationMessage implements Localization {
    private final MessageSource messageSource;
    private final LocalizationPropertie localizationPropertie;

    @Override
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage("identify.name", args, localizationPropertie.getLocale());
    }

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage("identify.name", null, localizationPropertie.getLocale());
    }
}