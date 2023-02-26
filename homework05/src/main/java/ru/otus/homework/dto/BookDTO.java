package ru.otus.homework.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private Date releaseDate;
    private List<Long> genreIds;
    private List<Long> authorIds;
}