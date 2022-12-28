package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;

@RequiredArgsConstructor
@Service
public class IdentityServiceImpl implements IdentityService {

    private final IOService ioService;

    @Override
    public String askName() {
        ioService.outputString("Name:");
        String name = ioService.readString().trim();
        if(name.isEmpty()){
            ioService.outputString("Name can't be empty.");
            return askName();
        }
        return name;
    }

    @Override
    public String askSurname() {
        ioService.outputString("Surname:");
        String surname = ioService.readString().trim();
        if(surname.isEmpty()){
            ioService.outputString("Surname can't be empty.");
            return askSurname();
        }
        return surname;
    }
}