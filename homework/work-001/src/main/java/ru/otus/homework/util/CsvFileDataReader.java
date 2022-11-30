package ru.otus.homework.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class CsvFileDataReader implements DataReader {
    private final String fileName;

    private Path filePath() throws URISyntaxException {
        URL uri = ClassLoader.getSystemResource(fileName);
        if (null == uri){
            throw new RuntimeException("Quiz CSV file path converting to URI error");
        }
        return Paths.get(uri.toURI());
    }

    private List<List<String>> prepareData() {
        final List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath().toFile()));) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException | URISyntaxException e) {
            throw new RuntimeException("The quiz CSV file is not found");
        } catch (IOException e) {
            throw new RuntimeException("Quiz CSV file reading error");
        } catch (CsvValidationException e) {
            throw new RuntimeException("Quiz CSV file preparation error");
        }
        return records;
    }

    @Override
    public List<List<String>> readLines() {
        return prepareData();
    }
}