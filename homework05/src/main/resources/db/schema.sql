DROP TABLE IF EXISTS book_genre;
DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS book;

CREATE TABLE genre(`id` IDENTITY NOT NULL PRIMARY KEY,
                   `title` VARCHAR(255) NOT NULL
);
ALTER TABLE genre ADD CONSTRAINT genre_title_unique UNIQUE(`title`);

CREATE TABLE author(`id` IDENTITY NOT NULL PRIMARY KEY,
                    `name` VARCHAR(32) NOT NULL,
                    `surname` VARCHAR(64) NOT NULL
);

CREATE TABLE book(`id` IDENTITY NOT NULL PRIMARY KEY,
                  `title` VARCHAR(512) NOT NULL,
                  `release_date` DATE NOT NULL
);

CREATE TABLE book_genre(`book_id` BIGINT NOT NULL,
                        `genre_id` BIGINT NOT NULL,
           foreign key (`book_id`) references book(`id`),
           foreign key (`genre_id`) references genre(`id`)
);
ALTER TABLE book_genre ADD CONSTRAINT book_genre_unique UNIQUE(`book_id`, `genre_id`);

CREATE TABLE book_author(`book_id` BIGINT NOT NULL,
                         `author_id` BIGINT NOT NULL,
                         foreign key (`book_id`) references book(`id`),
                         foreign key (`author_id`) references author(`id`)
);
ALTER TABLE book_author ADD CONSTRAINT book_author_unique UNIQUE(`book_id`, `author_id`);