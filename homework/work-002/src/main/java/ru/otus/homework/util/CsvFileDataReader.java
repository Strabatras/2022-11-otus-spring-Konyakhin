package ru.otus.homework.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class CsvFileDataReader implements DataReader {
    private final String fileName;

    private InputStream fileFromResourceAsStream(String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("File name is empty");
        }
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File is not found");
        }
        return inputStream;
    }

    private List<List<String>> prepareData() throws IOException {
        final List<List<String>> records = new ArrayList<>();

        try (final InputStream inputStream = fileFromResourceAsStream(fileName);
             final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
             final CSVReader csvReader = new CSVReader(inputStreamReader)) {
                String[] values;
                while ((values = csvReader.readNext()) != null) {
                    records.add(Arrays.asList(values));
                }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    @Override
    public List<List<String>> readLines() throws IOException {
        return prepareData();
    }
}