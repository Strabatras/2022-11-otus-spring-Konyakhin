package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;
import ru.otus.homework.util.Localization;

@RequiredArgsConstructor
@Service
public class IdentityServiceImpl implements IdentityService {

    private final IOService ioService;
    private final Localization localization;

    @Override
    public String askName() {
        ioService.outputString(localization.getMessage("identify.name") + ":");
        String name = ioService.readString().trim();
        if(name.isEmpty()){
            ioService.outputString(localization.getMessage("identify.name.cant.be.empty"));
            return askName();
        }
        return name;
    }

    @Override
    public String askSurname() {
        ioService.outputString(localization.getMessage("identify.surname") + ":");
        String surname = ioService.readString().trim();
        if(surname.isEmpty()){
            ioService.outputString(localization.getMessage("identify.surname.cant.be.empty"));
            return askSurname();
        }
        return surname;
    }
}