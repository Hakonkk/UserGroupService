CREATE TABLE users
(
    id       SERIAL PRIMARY KEY UNIQUE NOT NULL,
    username VARCHAR(255)           NOT NULL
);