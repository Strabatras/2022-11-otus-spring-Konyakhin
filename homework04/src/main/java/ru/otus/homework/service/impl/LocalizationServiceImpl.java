package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.properties.LocalizationPropertie;
import ru.otus.homework.service.LocalizationService;

@RequiredArgsConstructor
@Service
public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private final LocalizationPropertie localizationPropertie;

    @Override
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, localizationPropertie.getLocale());
    }

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, localizationPropertie.getLocale());
    }
}