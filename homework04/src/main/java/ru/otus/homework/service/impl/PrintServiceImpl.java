package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.PrintService;

@RequiredArgsConstructor
@Service
public class PrintServiceImpl implements PrintService {
    private final IOService ioService;
    private final LocalizationService localizationService;

    public void outputMessage(String message){
        ioService.outputString(message);
    }

    @Override
    public void outputLocalizedMessage(String localizationCode) {
        ioService.outputString(localizationService.getMessage(localizationCode));
    }

    @Override
    public void outputLocalizedMessage(String localizationCode, Object ...localizationArgs) {
        ioService.outputString(localizationService.getMessage(localizationCode, localizationArgs));
    }
}