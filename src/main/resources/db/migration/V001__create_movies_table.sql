CREATE TABLE movies
(
    id   SERIAL
        CONSTRAINT pk_movies_id PRIMARY KEY,
    name VARCHAR(255)
);
