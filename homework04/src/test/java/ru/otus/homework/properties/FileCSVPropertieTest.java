package ru.otus.homework.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.CSV_FILE_NAME;
import static ru.otus.homework.DataFactory.CSV_FOLDER_NAME;

@DisplayName("Параметры хранения CSV файла")
@SpringBootTest
class FileCSVPropertieTest {

    @Autowired
    private FileCSVPropertie fileCSVPropertie;

    @DisplayName("возвращается корректное название директории хранения")
    @Test
    void shouldReturnCorrectFolder() {
        assertThat(fileCSVPropertie.getFolder()).isEqualTo(CSV_FOLDER_NAME);
    }

    @DisplayName("возвращается корректное название файла")
    @Test
    void shouldReturnCorrectFileName() {
        assertThat(fileCSVPropertie.getFileName()).isEqualTo(CSV_FILE_NAME);
    }
}