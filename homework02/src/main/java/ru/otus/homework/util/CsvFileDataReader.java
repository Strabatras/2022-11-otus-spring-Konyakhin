package ru.otus.homework.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.exception.EmptyFileNameQuizException;
import ru.otus.homework.exception.FileNotFoundQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.exception.LineValidationQuizException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class CsvFileDataReader implements DataReader {
    private final String fileName;

    @Override
    public List<List<String>> readLines() {
        return prepareData();
    }

    private InputStream fileFromResourceAsStream(String fileName) {
        if (fileName.isEmpty()) {
            throw new EmptyFileNameQuizException("File name is empty");
        }
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundQuizException("File not found");
        }
        return inputStream;
    }

    private List<List<String>> prepareData() {
        final List<List<String>> records = new ArrayList<>();

        try (final InputStream inputStream = fileFromResourceAsStream(fileName);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             final CSVReader csvReader = new CSVReader(inputStreamReader)) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (CsvValidationException e) {
            throw new LineValidationQuizException("Line reading error", e);
        } catch (IOException e) {
            throw new IOQuizException("Read data error", e);
        }
        return records;
    }
}