package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;
import ru.otus.homework.service.PrintService;

@RequiredArgsConstructor
@Service
public class IdentityServiceImpl implements IdentityService {

    private final IOService ioService;
    private final PrintService printService;

    @Override
    public String askName() {
        printService.outputLocalizedMessage("identify.name", ":");
        String name = ioService.readString().trim();
        if(name.isEmpty()){
            printService.outputLocalizedMessage("identify.name.cant.be.empty");
            return askName();
        }
        return name;
    }

    @Override
    public String askSurname() {
        printService.outputLocalizedMessage("identify.surname", ":");
        String surname = ioService.readString().trim();
        if(surname.isEmpty()){
            printService.outputLocalizedMessage("identify.surname.cant.be.empty");
            return askSurname();
        }
        return surname;
    }
}