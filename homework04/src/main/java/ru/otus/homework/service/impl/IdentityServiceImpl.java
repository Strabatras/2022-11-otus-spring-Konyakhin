package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;
import ru.otus.homework.service.LocalizationService;

@RequiredArgsConstructor
@Service
public class IdentityServiceImpl implements IdentityService {

    private final IOService ioService;
    private final LocalizationService localizationService;

    @Override
    public String askName() {
        ioService.outputString(localizationService.getMessage("identify.name") + ":");
        String name = ioService.readString().trim();
        if(name.isEmpty()){
            ioService.outputString(localizationService.getMessage("identify.name.cant.be.empty"));
            return askName();
        }
        return name;
    }

    @Override
    public String askSurname() {
        ioService.outputString(localizationService.getMessage("identify.surname") + ":");
        String surname = ioService.readString().trim();
        if(surname.isEmpty()){
            ioService.outputString(localizationService.getMessage("identify.surname.cant.be.empty"));
            return askSurname();
        }
        return surname;
    }
}