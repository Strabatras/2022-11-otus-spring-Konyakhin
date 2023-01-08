INSERT INTO genre (`title`) VALUES ('Научная фантастика');
INSERT INTO genre (`title`) VALUES ('Ужасы');
INSERT INTO genre (`title`) VALUES ('Фэнтези');
INSERT INTO genre (`title`) VALUES ('Фантастика');

INSERT INTO author (`name`, `surname`) VALUES ('Дин', 'Кунц');
INSERT INTO author (`name`, `surname`) VALUES ('Терри', 'Пратчетт');
INSERT INTO author (`name`, `surname`) VALUES ('Нил', 'Гейман');
INSERT INTO author (`name`, `surname`) VALUES ('Айзек', 'Азимов');

INSERT INTO book (`title`, `release_date`) VALUES ('Прелюдия к Основанию', '1988-09-16');
INSERT INTO book (`title`, `release_date`) VALUES ('Путь к Основанию', '1993-06-06');
INSERT INTO book (`title`, `release_date`) VALUES ('Ангелы-хранители', '1987-04-21');
INSERT INTO book (`title`, `release_date`) VALUES ('Благие знамения', '1990-05-01');
INSERT INTO book (`title`, `release_date`) VALUES ('Цвет волшебства', '1983-03-08');
INSERT INTO book (`title`, `release_date`) VALUES ('Звёздная пыль', '1999-01-02');

INSERT INTO book_genre (`book_id`, `genre_id`) VALUES (1, 1);
INSERT INTO book_genre (`book_id`, `genre_id`) VALUES (2, 1);
INSERT INTO book_genre (`book_id`, `genre_id`) VALUES (3, 2);
INSERT INTO book_genre (`book_id`, `genre_id`) VALUES (4, 3);
INSERT INTO book_genre (`book_id`, `genre_id`) VALUES (5, 3);
INSERT INTO book_genre (`book_id`, `genre_id`) VALUES (6, 4);

INSERT INTO book_author (`book_id`, `author_id`) VALUES (1, 4);
INSERT INTO book_author (`book_id`, `author_id`) VALUES (2, 4);
INSERT INTO book_author (`book_id`, `author_id`) VALUES (3, 1);
INSERT INTO book_author (`book_id`, `author_id`) VALUES (4, 2);
INSERT INTO book_author (`book_id`, `author_id`) VALUES (4, 3);
INSERT INTO book_author (`book_id`, `author_id`) VALUES (5, 2);
INSERT INTO book_author (`book_id`, `author_id`) VALUES (6, 3);
