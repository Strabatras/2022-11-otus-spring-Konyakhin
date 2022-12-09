package ru.otus.homework.util;

import java.io.IOException;
import java.util.List;

public interface DataReader {
    List<List<String>> readLines() throws IOException;
}